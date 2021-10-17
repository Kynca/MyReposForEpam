package by.training.finaltask.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class EncodingFilter implements Filter {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        debugLog.debug("in encoding filter");
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setCharacterEncoding("UTF-8");
        debugLog.debug(servletRequest.getLocale().getLanguage() + servletRequest.getLocale().getCountry());

        httpResponse.setHeader("Cache-Control","no-cache");
        httpResponse.setHeader("Pragma","no-cache");
        httpResponse.setDateHeader("Expires",0);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
