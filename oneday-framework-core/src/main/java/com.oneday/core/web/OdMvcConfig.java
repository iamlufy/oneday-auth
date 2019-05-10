package com.oneday.core.web;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.base.Charsets;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangzf
 * @date 2019/1/8 10:25
 */
@Configuration
@ConditionalOnWebApplication
public class OdMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // http消息转换
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<>();
        stringMediaTypes.add(MediaType.TEXT_PLAIN);
        stringMediaTypes.add(MediaType.TEXT_XML);
        stringMediaTypes.add(MediaType.APPLICATION_JSON_UTF8 );
        stringConverter.setSupportedMediaTypes(stringMediaTypes);
        converters.add(0, stringConverter);

        // fastjson 的配置信息
        FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charsets.UTF_8);
        fastJsonConfig.setFeatures(Feature.IgnoreNotMatch);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(
                // key 值加引号
                SerializerFeature.QuoteFieldNames,
                // 空值处理
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
                // 处理循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                // 格式化日期
                SerializerFeature.WriteDateUseDateFormat);

        jsonConverter.setFastJsonConfig(fastJsonConfig);
        // json
        List<MediaType> jsonMediaTypes = new ArrayList<>();
        jsonMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jsonConverter.setSupportedMediaTypes(jsonMediaTypes);
        converters.add(1, jsonConverter);
    }



}
