package com.dennistocker.demo.common.config;

import com.dennistocker.demo.common.Interceptor.LoginInterceptor;
import com.dennistocker.demo.common.Interceptor.MyWebRequestInterceptor;
import com.dennistocker.demo.common.resolver.CurrentUserMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @date 2021/5/25 10:31 上午
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor handlerInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    MyWebRequestInterceptor webRequestInterceptor() {
        return new MyWebRequestInterceptor();
    }

    @Bean
    public CurrentUserMethodArgumentResolver argumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    // 如果不在ResultBodyAdvice中对String特殊处理
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位，使得先让 json 转换器处理返回值，这样 String转换器就处理不了了。
//        converters.add(0, new MappingJackson2CborHttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，不使用String类型的转换器
//        converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**");

        registry.addWebRequestInterceptor(webRequestInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(argumentResolver());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }
}
