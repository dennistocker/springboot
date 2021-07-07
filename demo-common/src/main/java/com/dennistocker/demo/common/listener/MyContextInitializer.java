package com.dennistocker.demo.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @date 2021/6/29 2:33 下午
 * Spring容器刷新之前的回调
 */


/**
 *  一般不用自定义ApplicationContextInitializer，SpringBoot默认注册了3个
 *  可在spring.factories中配置或者SpringApplication.addInitializers()
 *
 * org.springframework.context.ApplicationContextInitializer=
 * \org.springframework.boot.context.ConfigurationWarningsApplication-ContextInitializer,
 * \org.springframework.boot.context.ContextIdApplicationContextInitia-lizer,
 * \org.springframework.boot.context.config.DelegatingApplicationContex-tInitializer
 *
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        log.info("=== MyContextInitializer initialize ===");
    }
}
