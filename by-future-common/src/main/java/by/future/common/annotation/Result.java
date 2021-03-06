package by.future.common.annotation;

import java.lang.annotation.*;

/**
 * 返回给前台值统一格式
 *
 * @author by@Deng
 * @create 2017-12-15 09:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface Result{

    int resultCode() default 0;

}
