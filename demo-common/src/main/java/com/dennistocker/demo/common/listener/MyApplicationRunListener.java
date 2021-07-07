package com.dennistocker.demo.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 *  spring.factories中配置
 */
@Slf4j
public class MyApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;
    private final String[] args;

    MyApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log.info("=== MyApplicationRunListener starting ===");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log.info("=== MyApplicationRunListener environmentPrepared ===");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("=== MyApplicationRunListener contextPrepared ===");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("=== MyApplicationRunListener contextLoaded ===");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("=== MyApplicationRunListener started ===");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("=== MyApplicationRunListener running ===");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("=== MyApplicationRunListener failed ===");
    }
}
