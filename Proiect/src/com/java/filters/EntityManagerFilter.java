package com.java.filters;

import javax.persistence.EntityManager;
import javax.servlet.*;
import java.io.IOException;
import com.java.persistance.PersistenceUtil;

public class EntityManagerFilter implements Filter {
    private EntityManager entityManager ;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        entityManager= PersistenceUtil.createEntityManager();

        filterChain.doFilter(servletRequest,servletResponse);

        PersistenceUtil.closeEntityManager();
    }

    public void destroy() {

    }
}
