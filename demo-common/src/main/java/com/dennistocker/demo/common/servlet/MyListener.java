package com.dennistocker.demo.common.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @date 2021/6/30 11:19 上午
 */
@Slf4j
@WebListener
public class MyListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("MyListener requestInitialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("MyListener requestDestroyed");
    }
}
