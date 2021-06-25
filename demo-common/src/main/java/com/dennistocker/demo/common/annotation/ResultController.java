package com.dennistocker.demo.common.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @date 2021/5/24 5:59 下午
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResultBody
public @interface ResultController {
}
