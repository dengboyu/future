package by.future.common.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 脏话过滤器
 *
 * @author by@Deng
 * @create 2017-10-11 08:52
 */
@WebFilter(filterName = "DirtyFilter")
public class DirtyFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

//        DirtyRequestWrapper dirtyRequestWrapper = new DirtyRequestWrapper(request);
        chain.doFilter(request, res);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
