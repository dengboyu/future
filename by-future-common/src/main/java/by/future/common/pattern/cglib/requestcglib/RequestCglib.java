package by.future.common.pattern.cglib.requestcglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * request强求方法增强,用于结局request的get请求乱码问题
 *
 * @author by@Deng
 * @create 2017-12-09 19:34
 */
public class RequestCglib {

    private HttpServletRequest request; //目标类(只是一个实例，这儿需要一个实现类)

    public RequestCglib(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 使用cglib让request增强
     * 底层是创建一个子类进行包装增强
     * @author by@Deng
     * @date 2017/12/9 下午7:38
     */
    public HttpServletRequest requestEnhancer(){
        //获取enhancer增强类
        Enhancer enhancer = new Enhancer();

        //确定父方法
        enhancer.setSuperclass(request.getClass());

        //代理类执行,回调通知
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //前置通知


                //执行原类方法
                Object obj = method.invoke(request,objects);

                //后置通知

                return obj;
            }
        });


        //返回增强类
        return (HttpServletRequest) enhancer.create();
    }

}
