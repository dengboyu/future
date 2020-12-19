package by.future.servicebiz.validation.instance;

import by.future.common.exception.ValidationException;
import by.future.common.utils.StringUtils;
import by.future.servicebiz.validation.RequestValidator;

import java.util.Map;

/**
 * @author by@Deng
 * @create 2020-12-20 00:58
 */
public class LoginRequestValidator implements RequestValidator<Map<String, String>> {

    @Override
    public void validate(Map<String, String> request) throws ValidationException {

        if (request == null) throw new ValidationException(100,"错误");

        if(StringUtils.isEmpty(request.get("u2"))) throw new ValidationException(100,"参数u1为空");

    }


}
