package com.dennistocker.demo.common.listener;

import com.dennistocker.demo.common.event.MyCustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @date 2021/6/29 5:46 下午
 */

@Slf4j
@Component
public class MyCustomEventListener implements ApplicationListener<MyCustomEvent> {
    @Override
    public void onApplicationEvent(MyCustomEvent myCustomEvent) {
        log.info("-- MyCustomEventListener: eventName: {}, eventSource: {}", myCustomEvent.getEventName(), myCustomEvent.getEventSource());
    }
}
