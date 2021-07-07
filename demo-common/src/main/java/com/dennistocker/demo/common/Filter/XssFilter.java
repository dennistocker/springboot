package com.dennistocker.demo.common.Filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 2021/6/30 1:29 下午
 */
public class XssFilter implements Filter {

    private boolean xssEnabled = false;
    private String[] xssExcludeUrls;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String enabled = filterConfig.getInitParameter("enabled");
        String excludeUrls = filterConfig.getInitParameter("excludeUrls");
        if (StringUtils.hasText(enabled)) {
            this.xssEnabled = Boolean.parseBoolean(enabled);
        }
        if (StringUtils.hasText(excludeUrls)) {
            this.xssExcludeUrls = excludeUrls.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getServletPath();
        if (!xssEnabled || isExcludeUrl(url)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(xssRequest, servletResponse);
    }

    private boolean isExcludeUrl(String url) {
        if (xssExcludeUrls == null || xssExcludeUrls.length == 0) {
            return false;
        }
        for (String pattern : xssExcludeUrls) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }
}
