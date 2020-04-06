package by.future.common.beaninit.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author by@Deng
 * @create 2020-04-04 18:53
 */
@Component
public class UserLoginBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        GenericBeanDefinition beanDefinition= (GenericBeanDefinition) beanFactory.getBeanDefinition("userLogin");

        System.out.println("beanDefinition元信息："+beanDefinition);

    }
}
