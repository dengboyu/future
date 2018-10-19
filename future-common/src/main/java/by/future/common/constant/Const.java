package by.future.common.constant;

/**
 * 常量
 *
 * @author by@Deng
 * @create 2018-09-02 23:41
 */
public final class Const {

    /** utf-8编码 */
    public static final String CHARSET_UTF8 = "UTF-8";

    /** gbk编码 */
    public static final String CHARSET_GBK = "GBK";

    /** 英文字符集编码 */
    public static final String CHARSET_LATIN = "ISO-8859-1";

    /** 日期格式 */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /** 日期时间格式 */
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /** 时间戳格式 */
    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    
    /** json成功标记 */
    public static final String JSON_SUCCESS = "success";

    /** json错误标记 */
    public static final String JSON_FAIL = "fail";

    /** json返回信息 */
    public static final String JSON_MESSAGE = "message";

    /** Cookie键值：验证键值分隔符 */
    public static final String COOKIE_VALIDATE_KEY_SPLIT="$_";

    /** 请求属性键值：当前用户标识 */
    public static final String CUR_USER_ID= "CUR_USER_ID";

    /** 请求属性键值：当前用户名称 */
    public static final String CUR_USER_NAME = "CUR_USER_NAME";



    /*****************************微信公众号、小程序公共常量******************************/
    /** 微信accessToken失效时间(1小时50分钟) */
    public static final Long LOSETIME = 110 * 60 * 1000L;

    /** 异步支付结果通知的回调地址 */
    public static final String NOTIFY_URL = "https://www.xxx.com/Travelwill_Wap/weiXinReturn";

    /**  与接口配置信息中的Token要一致 */
    public static final String TOKEN = "tongyouhui";





}
