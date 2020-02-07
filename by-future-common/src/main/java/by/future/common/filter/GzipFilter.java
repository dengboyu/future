package by.future.common.filter;

import by.future.common.pattern.proxy.responseProxy.GzipResponseProxy;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * 全栈压缩数据输出
 *
 * @author by@Deng
 * @create 2017-10-11 20:34
 */
@WebFilter(filterName = "GzipFilter")
public class GzipFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //对response进行增强
//        GzipResponseWrapper gzipResponseWrapper = new GzipResponseWrapper(response);

        //对response进行动态代理
        GzipResponseProxy gzipResponseProxy = new GzipResponseProxy(response);
        chain.doFilter(req, gzipResponseProxy.getProxy());

        //拿出缓存中的数据，压缩后输出给浏览器
        byte[] out = gzipResponseProxy.getBuffer();
        System.out.println("数据原始大小为:"+out.length);

        ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();  //缓存流
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bufferOut);
        gzipOutputStream.write(out);
        gzipOutputStream.close();

        byte[] gzip = bufferOut.toByteArray();
        System.out.println("数据压缩大小为:"+gzip.length);

        response.setHeader("content-encoding","gzip");
        response.setContentLength(gzip.length);
        response.getOutputStream().write(gzip);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
