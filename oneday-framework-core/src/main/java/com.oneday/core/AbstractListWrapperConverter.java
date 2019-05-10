package com.oneday.core;

import com.alibaba.fastjson.JSON;
import com.oneday.core.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Collections;
import java.util.List;

/**
 * 菜单路径转JSON／生成对象 转换器
 *
 * @author zzf
 */
public abstract class AbstractListWrapperConverter<T> implements AttributeConverter<List<T>, String> {
    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        return JSON.toJSONString(attribute == null ? Collections.emptyList() : attribute);
    }

    /**
     * 子类设置需要转换的class
     *
     * @return
     */
    protected abstract Class<T> getConverterClass();
    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return Collections.emptyList();
        }
        return JSON.parseArray(dbData).toJavaList(getConverterClass());
    }

}
