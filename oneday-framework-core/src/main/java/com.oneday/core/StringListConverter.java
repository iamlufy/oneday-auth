package com.oneday.core;

/**
 * @author zhuangzf
 * @date 2019/2/14 8:57
 */
public class StringListConverter extends AbstractListWrapperConverter<String>{
    @Override
    protected Class<String> getConverterClass() {
        return String.class;
    }
}
