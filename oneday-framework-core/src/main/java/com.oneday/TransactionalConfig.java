package com.oneday;

import com.google.common.collect.Lists;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzf
 */
@Configuration
public class TransactionalConfig  {

    private static final String CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME = "serviceTransactionInterceptor";


    /**
     * 可传播事务配置
     */
    private static final String[] DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
            "add*",
            "save*",
            "insert*",
            "delete*",
            "update*",
            "edit*",
            "batch*",
            "create*",
            "remove*",
    };
    /**
     * 默认的只读事务
     */
    private static final String[] DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
            "get*",
            "count*",
            "find*",
            "query*",
            "select*",
            "list*",
            "*",
    };

    /**
     * 默认只对 "*Service" , "*ServiceImpl" Bean 进行事务处理,"*"表示模糊匹配, 比如 : userService,orderServiceImpl
     */
    private static final String[] DEFAULT_TRANSACTION_BEAN_NAMES = {"*Service", "*ServiceImpl"};


    /**
     * 配置事务拦截器
     *
     */

    @Bean( CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME )
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {

        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnly = this.readOnlyTransactionRule();
        RuleBasedTransactionAttribute required = this.requiredTransactionRule();
        // 默认的只读事务配置
        for (String methodName : DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
            transactionAttributeSource.addTransactionalMethod(methodName, readOnly);
        }
        // 默认的传播事务配置
        for (String methodName : DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
            transactionAttributeSource.addTransactionalMethod(methodName, required);
        }

        return new TransactionInterceptor(platformTransactionManager, transactionAttributeSource);

    }

    /**
     * 支持当前事务;如果不存在创建一个新的
     * {@link org.springframework.transaction.annotation.Propagation#REQUIRED}
     */
    private RuleBasedTransactionAttribute requiredTransactionRule() {
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        //设置回滚异常
        required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        required.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);
        return required;
    }

    /**
     * 只读事务
     * {@link org.springframework.transaction.annotation.Propagation#NOT_SUPPORTED}
     */
    private RuleBasedTransactionAttribute readOnlyTransactionRule() {
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly(true);
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        return readOnly;
    }

    /**
     * 配置事务拦截
     * <p>
     */
    @Bean
    public BeanNameAutoProxyCreator customizeTransactionBeanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        // 设置定制的事务拦截器
        beanNameAutoProxyCreator.setInterceptorNames(CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME);
        List<String> transactionBeanNames = Lists.newArrayListWithCapacity(DEFAULT_TRANSACTION_BEAN_NAMES.length);
        // 默认
        transactionBeanNames.addAll(Arrays.asList(DEFAULT_TRANSACTION_BEAN_NAMES));
        // 归集
        for (String transactionBeanName : transactionBeanNames) {
            beanNameAutoProxyCreator.setBeanNames(transactionBeanName);
        }

        beanNameAutoProxyCreator.setProxyTargetClass(true);
        return beanNameAutoProxyCreator;
    }
}
