package org.example.emarketmall.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRequestHolder implements Filter {

    private static ThreadLocal<HttpServletRequest> httpServletRequestHolder = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> httpServletResponseHolder = new ThreadLocal<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 绑定到当前线程
        httpServletRequestHolder.set((HttpServletRequest) request);
        httpServletResponseHolder.set((HttpServletResponse) response);
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            httpServletRequestHolder.remove(); // 清理资源引用
            httpServletResponseHolder.remove();
        }
    }

    @Override
    public void destroy() {
    }

    public static HttpServletRequest getRequest() {
        return httpServletRequestHolder.get();
    }

    public static HttpServletResponse getResponse() {
        return httpServletResponseHolder.get();
    }

}
