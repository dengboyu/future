package by.future.common.filter;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 静态资源缓存过滤器
 *
 * @author by@Deng
 * @create 2017-10-09 22:28
 */
@WebFilter(filterName = "BufferFilter")
public class BufferFilter implements Filter {

    private FilterConfig filterConfig;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        //1.获取用户想访问的资源
        String uri = request.getRequestURI();

        //2.得到想访问资源的后缀名
        String ext = uri.substring(uri.lastIndexOf(".")+1);

        //3.得到资源缓存时间
        String time = filterConfig.getInitParameter(ext);

        if(StringUtils.isNotEmpty(time)){
             response.setDateHeader("Expires", System.currentTimeMillis() + Long.valueOf(time) * 3600 * 1000);
        }

        chain.doFilter(req,res);
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    public void destroy() {

    }

}
