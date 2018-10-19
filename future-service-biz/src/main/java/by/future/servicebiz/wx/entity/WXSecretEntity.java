package by.future.servicebiz.wx.entity;

import java.util.Date;

/**
 * 微信实体类
 *
 * @author by@Deng
 * @create 2018-04-28 10:14
 */
public class WXSecretEntity {

    //属性字段
    private Integer id; // 自增主键
    private String appSecret; // 公众号或者微信的secret
    private String mchId; // 商户后台账号
    private String payKey; // 支付用到的key
    private String attach; // 附加信息
    private String body; // 商品描述
    private String description; // 商品描述信息
    private String token; // 公众号的token
    private String certificate; // 退款证书路径
    private Date createTime; //
    private Date updateTime; //
    private String userId; // 用户的主键
    private Integer type; // 公众号0   小程序 1
    private Integer identity; // 0 供应商  1分销商的标识   ---小程序或者公众号
    private Integer collection; // 分销商把产品添加到小程序形式：0 自动   1 手动
    private String accessToken; // 微信唯一访问凭证
    private Date loseTime; // 微信access_token失效时间（1个小时50分钟）
    private String wxJsapiTicket; // 记录jsapi_ticket的值
    private String appId; // 公众号或者微信的app_id


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(Date loseTime) {
        this.loseTime = loseTime;
    }

    public String getWxJsapiTicket() {
        return wxJsapiTicket;
    }

    public void setWxJsapiTicket(String wxJsapiTicket) {
        this.wxJsapiTicket = wxJsapiTicket;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "WXSecretEntity{" +
                "id=" + id +
                ", appSecret='" + appSecret + '\'' +
                ", mchId='" + mchId + '\'' +
                ", payKey='" + payKey + '\'' +
                ", attach='" + attach + '\'' +
                ", body='" + body + '\'' +
                ", description='" + description + '\'' +
                ", token='" + token + '\'' +
                ", certificate='" + certificate + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", identity=" + identity +
                ", collection=" + collection +
                ", accessToken='" + accessToken + '\'' +
                ", loseTime=" + loseTime +
                ", wxJsapiTicket='" + wxJsapiTicket + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
