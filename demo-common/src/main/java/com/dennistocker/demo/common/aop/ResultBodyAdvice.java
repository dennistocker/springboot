package com.dennistocker.demo.common.aop;

import com.dennistocker.demo.common.Result;
import com.dennistocker.demo.common.ResultCode;
import com.dennistocker.demo.common.ServiceException;
import com.dennistocker.demo.common.annotation.ResultBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @date 2021/5/24 6:01 下午
 */

@RestControllerAdvice
public class ResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> RESPONSE_RESULT_ANNOTATION = ResultBody.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        return AnnotatedElementUtils.hasAnnotation(
                methodParameter.getContainingClass(),  RESPONSE_RESULT_ANNOTATION) ||
                methodParameter.hasMethodAnnotation(RESPONSE_RESULT_ANNOTATION);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result) {
            return 0;
        }
        if (o instanceof String) {
            try {
                return new ObjectMapper().writeValueAsString(Result.success(o));
            } catch (JsonProcessingException e) {
                return Result.failure(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return Result.success(o);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        Result<String> r = Result.failure(ResultCode.INVALID_PARAMETER);
        r.setData(e.getMessage());
        return r;
    }

    @ExceptionHandler(ServiceException.class)
    public Result<?> serviceExceptionHandler(ServiceException e) {
        return Result.failure(e);
    }


    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception ex, WebRequest request) {
        return Result.failure(ex.getMessage());
    }
}
