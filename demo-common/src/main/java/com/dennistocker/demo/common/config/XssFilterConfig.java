package com.dennistocker.demo.common.config;

import cn.hutool.core.util.StrUtil;
import com.dennistocker.demo.common.Filter.XssFilter;
import com.dennistocker.demo.common.jsonserializer.XssStringJsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.DispatcherType;

/**
 * @date 2021/6/30 1:57 下午
 */
@Configuration
public class XssFilterConfig {

    @Value("${xss.enabled}")
    private String enabled;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    @Value("${xss.excludeUrls}")
    private String excludes;

    @Bean
    public FilterRegistrationBean<XssFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<XssFilter> bean = new FilterRegistrationBean<>();
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        bean.setFilter(new XssFilter());
        bean.setName("XssFilter");
        bean.setOrder(Ordered.LOWEST_PRECEDENCE);
        bean.setUrlPatterns(StrUtil.split(urlPatterns, ","));
        bean.addInitParameter("excludeUrls", excludes);
        bean.addInitParameter("enabled", enabled);

        return bean;
    }

    @Bean
    @Primary
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule xssModule = new SimpleModule("XssStringJsonDeserializer");
        // 仅对入参进行处理即可
        xssModule.addDeserializer(String.class, new XssStringJsonDeserializer());
//        xssModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(xssModule);
        return objectMapper;
    }
}
