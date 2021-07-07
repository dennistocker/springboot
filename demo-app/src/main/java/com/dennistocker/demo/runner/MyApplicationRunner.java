package com.dennistocker.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @date 2021/6/29 6:08 下午
 */

@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("-- MyApplicationRunner --");
        for (String arg : args.getSourceArgs()) {
            log.info("-- MyApplicationRunner: {}", arg);
        }
    }
}
