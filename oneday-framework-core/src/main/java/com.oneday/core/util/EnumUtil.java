package com.oneday.core.util;

import com.oneday.core.exception.BaseException;
import com.oneday.core.global.GlobalEnum;
import com.oneday.core.global.IBaseCode;
import org.apache.commons.lang3.EnumUtils;

/**
 * @author zhuangzf
 * @date 2019/4/2 14:20
 */
public class EnumUtil {
    /**
     * 根据枚举名称获取枚举，抛异常
     *
     * @param e
     * @param name
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E getEnumByName(Class<E> e, String name) {
        return EnumUtils.getEnumList(e).stream().filter(e1 -> e1.name().equals(name)).findFirst().orElseThrow(() -> new BaseException(GlobalEnum.PARAMS_ERROR));
    }

    /**
     * 根据枚举名称获取枚举，不抛异常
     *
     * @param e
     * @param name
     * @param <E>
     * @return 查找不到返回null
     */
    public static <E extends Enum<E>> E getEnum(Class<E> e, String name) {
        return EnumUtils.getEnumList(e).stream().filter(e1 -> e1.name().equals(name)).findFirst().orElse(null);
    }
}
