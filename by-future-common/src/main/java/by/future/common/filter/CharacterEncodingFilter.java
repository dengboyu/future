package by.future.common.filter;


import by.future.entity.constant.SysConst;
import by.future.common.pattern.proxy.requestProxy.CharacterEncodingRequestProxy;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码过滤器
 *
 * @author by@Deng
 * @create 2017-10-11 07:42
 */
@WebFilter(filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

    private FilterConfig filterConfig;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String charset = filterConfig.getInitParameter("encoding");
        if(charset ==null) {
            charset = SysConst.CHARSET_UTF8;
        }

        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);

        //对request进行增强
        //CharacterRequestWrapper characterRequest = new CharacterRequestWrapper(request);

        //对request动态代理
        CharacterEncodingRequestProxy characterEncodingRequestProxy = new CharacterEncodingRequestProxy(request);

        //使用cglib动态代理
//        RequestCglib requestCglib = new RequestCglib(request);

        chain.doFilter(characterEncodingRequestProxy.getRequest(),res);
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    public void destroy() {

    }

}
