package by.future.common.pattern.proxy.requestProxy;


import by.future.entity.constant.SysConst;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 编码过滤器的request代理
 *
 * @author by@Deng
 * @create 2017-10-14 22:24
 */
public class CharacterEncodingRequestProxy {

    private HttpServletRequest request;

    public CharacterEncodingRequestProxy(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest(){
        //当前类加载器、被增强对象、执行方法
        return (HttpServletRequest) Proxy.newProxyInstance(CharacterEncodingRequestProxy.class.getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //如果是get请求方法
                if(StringUtils.equalsIgnoreCase(request.getMethod(),"get")){
                    if(!method.getName().equals("getParameter")){
                        return method.invoke(request,args);
                    }else{
                        String value = String.valueOf(method.invoke(request,args));
                        if(StringUtils.isEmpty(value)) return value;

                        return new String(value.getBytes("ISO-8859-1"), SysConst.CHARSET_UTF8);
                    }
                }

                return method.invoke(request,args); //执行目标类
            }
        });
    }
}
