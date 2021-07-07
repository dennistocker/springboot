package com.dennistocker.demo.common.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @date 2021/6/29 5:48 下午
 */

@Data
public class MyCustomEvent extends ApplicationEvent {

    private String eventName;
    private String eventSource;

    public MyCustomEvent(Object source) {
        super(source);
    }
}
