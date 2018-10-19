package by.future.servicebiz.wx.wxModule;


public class WechatCommon {

    /**
     * 获取微信公众号token地址
     */
    public static final String GetWeChatOfficialAccountTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 获取永久二维码ticket地址
     */
    public static final String GetQRTicketUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    /**
     * 通过ticket获取二维码的地址
     */
    public static final String GetQRCodeByTicket = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";


    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";


    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */

    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */

    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";


    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 用户授权地址
     */
    public static final String WEIXIN_USER_AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?";

    /**
     * code换取的网页授权access_token地址
     */
    public static final String WEIXIN_WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";

    /**
     * 检验授权凭证（access_token）是否有效
     */
    public static final String WEIXIN_CHECK_ACCESS_TOKEN_VALID = "https://api.weixin.qq.com/sns/auth?access_token=";

    /**
     * 刷新验证的access_token
     */
    public static final String WEIXIN_REFLESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=";

    /**
     * 拉取用户信息
     */
    public static final String WEIXIN_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=";

    /**
     * 获取用户基本信息
     */
    public static final String WEIXIN_BASE_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";


}
