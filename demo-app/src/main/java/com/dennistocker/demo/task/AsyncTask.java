package com.dennistocker.demo.task;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @date 2021/6/29 5:25 下午
 */
@Slf4j
@Component
public class AsyncTask {
    @SneakyThrows
    @Async
    public void asyncTask() {
        log.info("{} === BEGIN asyncTask ===", Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("{} === END asyncTask ===", Thread.currentThread().getName());
    }

    @SneakyThrows
    @Async
    public Future<String> asyncTask(String s) {
        log.info("{} === BEGIN asyncTask with result ===", Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("{} === END asyncTask with result ===", Thread.currentThread().getName());
        return AsyncResult.forValue(s);
    }
}
