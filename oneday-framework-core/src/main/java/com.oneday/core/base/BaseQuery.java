package com.oneday.core.base;

import com.google.common.collect.Lists;
import com.oneday.core.web.ProjectUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author zhuangzf
 * @date 2018/12/19 17:29
 */
public class BaseQuery  {

    private final static BaseQuery INSTANCE = new BaseQuery();

    public static Specification baseQuery() {
        return INSTANCE.getSpec();
    }
    public <T extends Unique> Specification<T> getSpec() {
        return  ((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = Lists.newArrayList();

            predicateList.add(criteriaBuilder.equal(root.<Long>get("tenantId"), ProjectUtil.getLoginId()));

            return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
        });
    }
}
