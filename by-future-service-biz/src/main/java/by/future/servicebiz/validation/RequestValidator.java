package by.future.servicebiz.validation;

import by.future.common.exception.ValidationException;

/**
 * 校验参数
 *
 * @author by@Deng
 * @create 2020-12-20 00:52
 */
public interface RequestValidator<T> {

    void validate(T request) throws ValidationException;

}
