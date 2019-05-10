package com.oneday.core.tenant;

import com.oneday.core.base.Unique;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 租户ID
 *
 * @author zhuangzf
 * @date 2019/3/28 14:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class TenantIdDO extends Unique {

    @Column(columnDefinition = "varchar(50) not null comment'租户ID'")
    private String tenantId;
}
