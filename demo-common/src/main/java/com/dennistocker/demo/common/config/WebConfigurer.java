package com.dennistocker.demo.common.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @date 2021/5/25 10:31 上午
 */
public class WebConfigurer implements WebMvcConfigurer {

    // 如果不在ResultBodyAdvice中对String特殊处理
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位，使得先让 json 转换器处理返回值，这样 String转换器就处理不了了。
//        converters.add(0, new MappingJackson2CborHttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，不使用String类型的转换器
//        converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }
}
