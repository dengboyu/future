package by.future.entity.common;


/**
 * 签名算法返回结果
 *
 * @Author：by@Deng
 * @Date：2018/6/1 10:14
 */
public class SignResultEntity {

    private int code;   //1正确，其他异常
    private String message; //错误信息
    private String sign; //签名


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
