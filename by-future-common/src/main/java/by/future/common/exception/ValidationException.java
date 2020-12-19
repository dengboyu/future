package by.future.common.exception;

/**
 * @author by@Deng
 * @create 2020-12-20 00:55
 */
public class ValidationException extends RuntimeException{

    private Integer errCode;
    private String errMes;

    public ValidationException(Integer errCode, String errMes) {
        this.errCode = errCode;
        this.errMes = errMes;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMes() {
        return errMes;
    }

    public void setErrMes(String errMes) {
        this.errMes = errMes;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "errCode=" + errCode +
                ", errMes='" + errMes + '\'' +
                '}';
    }
}
