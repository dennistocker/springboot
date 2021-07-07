package com.dennistocker.demo.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @date 2021/6/29 5:41 下午
 */

/**
 *
 *  三种方式
 *  1. @component
 *  2. spring.factories中配置
 *  3. SpringApplication.addListeners()
 *  可用来监听特定事件，自定义事件等
 *
 *  SpringBoot默认：
 *  org.springframework.context.ApplicationListener=
 * \org.springframework.boot.builder.ParentContextCloserApplicationListener,
 * \org.springframework.boot.cloudfoundry.VcapApplicationListener,
 * \org.springframework.boot.context.FileEncodingApplicationListener,
 * \org.springframework.boot.context.config.AnsiOutputApplicationListener,
 * \org.springframework.boot.context.config.ConfigFileApplicationListener,
 * \org.springframework.boot.context.config.DelegatingApplicationListener,
 * \org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicat-ionListener,
 * \org.springframework.boot.logging.ClasspathLoggingApplicationListener,
 * \org.springframework.boot.logging.LoggingApplicationListener
 */

@Slf4j
@Component
public class MyApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("=== onApplicationEvent: {}", applicationEvent.getClass());
    }
}
