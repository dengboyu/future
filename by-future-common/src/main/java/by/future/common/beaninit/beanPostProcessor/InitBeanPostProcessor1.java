package by.future.common.beaninit.beanPostProcessor;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后处理bean：实例化bean之后立即执行该代码
 *
 * @Author：by@Deng
 * @Date：2018/12/10 10:58
 */
@Component
public class InitBeanPostProcessor1 implements BeanPostProcessor {

    
    /**
     * 实例化、依赖注入完毕，在调用显示的初始化init-method之前完成一些定制的初始化任务
     * 
     * @Author：by@Deng
     * @Date：2018/12/10 11:04
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(StringUtils.equalsIgnoreCase(beanName,"userLogin")){
            System.out.println("=================userLogin在init-method之前第一次被代理");
        }
        if(StringUtils.equalsIgnoreCase(beanName,"serviceTest")){
            System.out.println("初始化完成第一个bean，再走入第二个bean");
        }

        return bean;

        //两个方法都不能返回null，若果返回null那么在后续初始化将报空指针异常或者通过getBean()方法获取不到bean实例对象
        //因为后置处理器从spring Ioc容器中取出bean实例对象没有再次放回Ioc容器中
    }


    /**
     * 初始化完毕时执行
     *
     * @Author：by@Deng
     * @Date：2018/12/10 11:05
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(StringUtils.equalsIgnoreCase(beanName,"userLogin")){
            System.out.println("=================userLogin在初始化完毕时被实例化了");
        }

        if(StringUtils.equalsIgnoreCase(beanName,"serviceTest")){
            System.out.println("走入第二个bean完成");
        }

        return bean;
    }
}
