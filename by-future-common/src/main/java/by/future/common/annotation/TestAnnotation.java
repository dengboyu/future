package by.future.common.annotation;

import org.springframework.core.annotation.AliasFor;

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

    @AliasFor(annotation = Result.class)
    int resultCode() default 0;

}
