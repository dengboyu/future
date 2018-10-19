package by.future.common.proxy.responseProxy;

import by.future.common.constant.Const;
import by.future.common.exception.ByException;
import by.future.common.exception.CodeEnum;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 压缩输出response代理
 *
 * @author by@Deng
 * @create 2017-10-14 23:18
 */
public class GzipResponseProxy {

    private HttpServletResponse response;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, Const.CHARSET_UTF8));

    public GzipResponseProxy(HttpServletResponse response) throws UnsupportedEncodingException {
        this.response = response;
    }

    public HttpServletResponse getProxy(){
        return (HttpServletResponse) Proxy.newProxyInstance(GzipResponseProxy.class.getClassLoader(), response.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //对getWriter和getOutputStream两个方法代理
                if(StringUtils.equalsIgnoreCase(method.getName(),"getWriter")){
                    return printWriter;
                }else if(StringUtils.equalsIgnoreCase(method.getName(),"getOutputStream")){
                    return new MyServletOutputStream(byteArrayOutputStream);
                }

                return method.invoke(response,args);
            }
        });
    }


    /**
     * 返回缓存数据
     * @author by@Deng
     * @date 2017/10/11 下午8:54
     */
    public byte[] getBuffer(){
        printWriter.close();
        try {
            if(byteArrayOutputStream!=null){
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            return null;
        } catch (Exception e) {
            throw new ByException(CodeEnum.UNKNOW_ERROR.getCode(), CodeEnum.UNKNOW_ERROR.getMessage());
        }
    }

}


class MyServletOutputStream extends ServletOutputStream {

    private ByteArrayOutputStream byteArrayOutputStream;

    public MyServletOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        this.byteArrayOutputStream.write(b);
    }
}
