package com.dennistocker.demo.common.Filter;

import cn.hutool.core.util.EscapeUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 实现特殊字符过滤，可使用
 * 1. cn.hutool
 * 2. org.jsoup.jsoup
 * 3. apache.common.lang3.StringEscapeUtils.ESCAPE_HTML4
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value =  super.getHeader(name);
        return EscapeUtil.escape(value);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return EscapeUtil.escape(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] escapedValues = new String[count];
        for (int i = 0; i < count; i++) {
            escapedValues[i] = EscapeUtil.escape(values[i]);
        }
        return escapedValues;
    }

    /**
     *  此处未对post的数据进行转换，如果post为json格式，使用自定义objectMapper处理
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return super.getInputStream();
    }
}
