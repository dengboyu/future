package by.future.common.commonenum;


/**
 * 统一返回参数
 *
 * @Author：by@Deng
 * @Date：2018/11/29 14:40
 */
public enum ResultCodeEnum {

    //系统类错误 SYSTEM_ 10000+
    //配置类错误 CONFIG_ 20000+
    //限制类错误 LIMIT_ 30000+
    //业务类错误 BUSINESS_ 40000+  业务类参数定义在自己项目中
    //签名类错误 SIGN_ 41000+
    //参数类错误 PARAM_ 50000+

    SUCCESS(1,"SUCCESS"),
    FAIL(-1,"请求失败"),

    SYSTEM_BUSY(10001,"系统繁忙"),
    SYSTEM_INVALID_PARAMETER(10002,"无效参数"),
    SYSTEM_INVALID_TOKEN(10003,"无效token"),

    CONFIG_NULL(20001,"未获取到配置"),
    CONFIG_LIMIT(20002,"限流"),

    LIMIT_FREQUENCY(30001,"请求频繁"),
    LIMIT_CONCURRENCY(30002,"重复请求"),


    SIGN_ERROR(41000,"签名错误"),
    SIGN_NULL(41001,"签名sign不能为空"),
    SIGN_KEY_NULL(41002,"签名key不能为空"),
    SIGN_TIME_NULL(41003,"签名时间戳不能为空"),
    SIGN_TIMEOUT(41004,"签名超时"),

    PARAM_ERROR(50001,"请求失败"), //参数错误
    PARAM_NULL(50002,"请求失败"), //参数为空

    ;


    private final int code;
    private final String message;


    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
