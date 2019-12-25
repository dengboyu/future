package by.future.entity.config;


import java.io.Serializable;

/**
 * @Author：by@Deng
 * @Date：2019/12/20 16:30
 */
public class OpenCommonConfig implements Serializable {

    public static final long serialVersionUID = -1L;

    /** 总开关：true,false */
    private boolean openFlag;

    /** 0 1与limitNums配合使用限制开关,1一般用于测试，0-用于生产 */
    private int type;

    /** 尾号配置 */
    private String limitNums;


    public boolean isOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(boolean openFlag) {
        this.openFlag = openFlag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLimitNums() {
        return limitNums;
    }

    public void setLimitNums(String limitNums) {
        this.limitNums = limitNums;
    }
}
