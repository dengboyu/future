package by.future.entity.config;


import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author：by@Deng
 * @Date：2019/12/20 16:30
 */
public class OpenCommonConfig implements Serializable,Comparable<OpenCommonConfig> {

    public static final long serialVersionUID = -1L;


    /** 总开关：true,false */
    private boolean openFlag;

    /** 0 1与limitNums配合使用限制开关,1一般用于测试，0-用于生产 */
    private int type;

    /** 尾号配置 */
    private String limitNums;


    public boolean isOpenFlag() { return openFlag; }

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

    @Override
    public boolean equals(Object obj) {

        if (Objects.isNull(obj)) return false;
        if (obj.getClass() != getClass()) return false;

        OpenCommonConfig openCommonConfig = (OpenCommonConfig) obj;

        return Objects.equals(this.openFlag, openCommonConfig.openFlag) &&
                Objects.equals(this.type, openCommonConfig.type) &&
                Objects.equals(this.limitNums, openCommonConfig.limitNums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpenFlag(), getType(), getLimitNums());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("openFlag",openFlag)
                .add("type",type)
                .add("limitNums",limitNums)
                .toString();
    }


    @Override
    public int compareTo(OpenCommonConfig o) {
        return Integer.compare(getType(),o.getType());
    }
}
