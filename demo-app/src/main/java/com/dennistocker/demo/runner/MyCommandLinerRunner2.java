package com.dennistocker.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @date 2021/6/29 3:11 下午
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCommandLinerRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("=== MyCommandLinerRunner2 run ===");
    }
}
