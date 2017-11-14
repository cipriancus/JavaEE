package connectionpool.filter;

import connectionpool.session.SessionData;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

public class SessionFilter implements Filter{
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpSession session=request.getSession();

        if(((HttpServletRequest) servletRequest).getParameter("method").equals("cps")){
            if(session.getAttribute("sessionData")==null){
                try {
                    Context ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/cps");
                    SessionData.setSessionID(session.getId());
                    SessionData.setDataSource(ds);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                SessionData.setDataSource(((SessionData)session.getAttribute("sessionData")).getDataSource());
                SessionData.setSessionID(((SessionData)session.getAttribute("sessionData")).getSessionID());
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig=null;
    }
}
