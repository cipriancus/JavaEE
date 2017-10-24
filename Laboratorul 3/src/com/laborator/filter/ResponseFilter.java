package com.laborator.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletResponseWrapper wrapper=new HttpServletResponseWrapper((HttpServletResponse)servletResponse){
            @Override
            public PrintWriter getWriter() throws IOException {
                return super.getWriter();
            }
        };

        filterChain.doFilter(servletRequest, wrapper);
        wrapper.getWriter().write("<footer>Copyright Infoiasi 2017</footer>");
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
