package by.future.servicebiz.wx.entity;

/**
 * Created by by on 2017/5/25.
 */
public class WeiXinEntity {

    private Long id;//微信表自增主键
    private String accessToken;//访问微信凭证
    private String wxJsapiTicket;//js访问票据验证(每一个半小时就替换)
    private String signature;//签名
    private String timeStampOn;//生成签名的时间戳
    private String nonceStr;//生成签名的随机串
    private String url;//生成signature的当前页面url
    private String ticket;//
    private String jsapiRicket;//
    private String sign;//支付签名
    private String prepayId;//支付用的支付id
    private String openId;//微信对公众号唯一标识
    private String paySign;//支付签名
    private String reg;//判断当前用户是否注册过，0为未注册 1为注册过
    private String userId;//当前用户的userId
    private Integer type;   // 0公众号  1小程序 accessToken

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getWxJsapiTicket() {
        return wxJsapiTicket;
    }

    public void setWxJsapiTicket(String wxJsapiTicket) {
        this.wxJsapiTicket = wxJsapiTicket;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimeStampOn() {
        return timeStampOn;
    }

    public void setTimeStampOn(String timeStampOn) {
        this.timeStampOn = timeStampOn;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getJsapiRicket() {
        return jsapiRicket;
    }

    public void setJsapiRicket(String jsapiRicket) {
        this.jsapiRicket = jsapiRicket;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "WeiXinEntity{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", wxJsapiTicket='" + wxJsapiTicket + '\'' +
                ", signature='" + signature + '\'' +
                ", timeStampOn='" + timeStampOn + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", url='" + url + '\'' +
                ", ticket='" + ticket + '\'' +
                ", jsapiRicket='" + jsapiRicket + '\'' +
                ", sign='" + sign + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", openId='" + openId + '\'' +
                ", paySign='" + paySign + '\'' +
                ", reg='" + reg + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                '}';
    }
}
