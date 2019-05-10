package com.oneday.core.tenant;

import lombok.Data;

import java.io.Serializable;

/**
 * 租户ID值对象
 *
 * @author zhuangzf
 * @date 2019/3/28 14:18
 */
@Data
public class TenantIdV implements Serializable {
    private String tenantId;
}
