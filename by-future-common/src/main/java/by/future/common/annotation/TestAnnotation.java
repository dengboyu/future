package by.future.common.annotation;

import java.lang.annotation.*;

/**
 * @Author by@Deng
 * @Date 2019-08-24 10:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Result
public @interface TestAnnotation{
}
