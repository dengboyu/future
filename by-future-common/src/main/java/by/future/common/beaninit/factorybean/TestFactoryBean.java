package by.future.common.beaninit.factorybean;

import by.future.common.utils.ApplicationContextUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean，是一个生产泛型bean的工厂bean
 * sqlSessionFactoryBean用的就是这个FactoryBean
 *
 * @author by@Deng
 * @create 2020-04-06 12:01
 */
@Component
public class TestFactoryBean implements FactoryBean<TestFactory> {

    @Override
    public TestFactory getObject() throws Exception {

        TestFactory testFactory = new TestFactory();
        testFactory.setAge(11);
        testFactory.setName("测试工厂bean");

        return testFactory;
    }

    @Override
    public Class<TestFactory> getObjectType() {
        return TestFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static void main(String[] args) {

        Object object= ApplicationContextUtils.getBean("testFactoryBean");
        System.out.println(JSON.toJSON(object));

    }
}
