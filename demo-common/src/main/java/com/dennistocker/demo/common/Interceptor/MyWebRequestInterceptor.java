package com.dennistocker.demo.common.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @date 2021/6/30 10:44 上午
 */
@Slf4j
public class MyWebRequestInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        log.info("=== MyWebRequestInterceptor: preHandle ===");
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
        log.info("=== MyWebRequestInterceptor: postHandle ===");
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {
        log.info("=== MyWebRequestInterceptor: afterCompletion ===");
    }
}
