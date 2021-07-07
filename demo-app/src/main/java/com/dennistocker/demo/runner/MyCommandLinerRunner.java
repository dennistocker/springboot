package com.dennistocker.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @date 2021/6/29 3:07 下午
 */
@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class MyCommandLinerRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("=== MyCommandLinerRunner run ===");
    }
}
