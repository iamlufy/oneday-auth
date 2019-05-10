package com.oneday.core.tenant;

import com.oneday.core.PageAble;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

/**
 * 租户相关查询基类
 *
 * @author zhuangzf
 * @date 2019/1/8 15:13
 */
@Data
public abstract class BaseQuery implements PageAble {
    private Integer pageNum;
    private Integer pageSize;

    protected abstract Specification getSpec();

    public Specification getSpec(String tenantCode) {
        return getSpec().and((root, query, criteriaBuilder) -> query.where(criteriaBuilder.equal(root.get("tenantCode"), tenantCode)).getRestriction());
    }

    public Specification getSpec1(String tenantCode) {
        return (root, query, criteriaBuilder) -> query.where(criteriaBuilder.equal(root.get("tenantCode"), tenantCode)).getRestriction();
    }
}
