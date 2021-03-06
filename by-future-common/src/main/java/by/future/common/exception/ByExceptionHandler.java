package by.future.common.exception;

import by.future.enums.commonenum.ResultCodeEnum;
import by.future.common.structure.entity.ResultEntity;
import by.future.common.utils.ResultDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 通过注解处理异常
 *
 * @author by@Deng
 * @create 2017-09-30 08:47
 */
//@ControllerAdvice
@RestControllerAdvice
public class ByExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ByException.class);

    @ExceptionHandler(Exception.class)
    public ResultEntity handleBadRequest(Exception e) {

        if(e instanceof ByException){
            //如果返回的是自定义异常
            ByException byException = (ByException) e;
            if(byException.getCode()==null){
                return ResultDataUtils.error(ResultCodeEnum.FAIL.getCode(),byException.getMessage());   //返回自定义异常信息
            }
            return ResultDataUtils.error(byException.getCode(),byException.getMessage());   //返回自定义异常信息
        }

        //除系统自定义异常外,其他统一返回系统错误
        logger.error("【系统异常】:",e);
        return ResultDataUtils.error(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage());
    }

}
