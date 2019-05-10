package com.oneday.core;

import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzf
 * @date 2018/9/10 10:46
 */
public interface Specific<T> extends Serializable {
    /**
     * 获取查询数据仓库的条件
     *
     * @return  Specification<T>
     */
    Specification<T> getSpec();

    default Specification<T> getSpec(String tenantCode) {
        return getSpec().and((root, query, criteriaBuilder) -> query.where(criteriaBuilder.equal(root.get("tenantCode"), tenantCode)).getRestriction());
    }
}
