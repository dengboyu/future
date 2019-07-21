package by.future.common.commonenum;

/**
 * 失效时间枚举类
 *
 * @author by@Deng
 * @create 2017-12-06 23:16
 */
public enum ExpireTimeEnum {

    NONE(0, "无固定期限"),              //无固定期限
    ONE_SEC(1, "1秒钟"),              //1秒钟
    FIVE_SEC(5, "5秒钟"),            //5秒钟
    TEN_SEC(10, "10秒钟"),            //10秒钟
    HALF_A_MIN(30, "30秒钟"),        //30秒钟
    ONE_MIN(60, "1分钟"),             //1分钟
    FIVE_MIN(5*60, "5分钟"),         //5分钟
    TEN_MIN(10*60, "10分钟"),         //10分钟
    TWENTY_MIN(20*60, "20分钟"),      //20分钟
    HALF_AN_HOUR(30*60, "30分钟"),    //30分钟
    ONE_HOUR(60*60, "1小时"),         //1小时
    TWO_HOUR(2*60*60, "2小时"),       //2小时
    ONE_DAY(24*60*60, "1天"),         //1天
    ONE_MON(30*24*60*60, "1个月"),    //1个月
    ONE_YEAR(365*24*60*60, "1年")    //1年
    ;


    private final int time; //时间
    private final String desc;  //描述

    ExpireTimeEnum(int time, String desc) {
        this.time = time;
        this.desc = desc;
    }


    /**
     * 获取具体时间
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     * 获取时间描述信息
     * @return
     */
    public String getDesc() {
        return desc;
    }



}
