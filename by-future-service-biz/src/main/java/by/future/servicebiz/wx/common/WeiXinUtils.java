package by.future.servicebiz.wx.common;

import by.future.common.constant.Const;
import by.future.common.utils.HttpClientUtils;
import by.future.common.utils.MapUtils;
import by.future.common.utils.UUIDUtils;
import by.future.common.utils.XMLUtils;
import by.future.servicebiz.wx.entity.WXPayInfoEntity;
import by.future.servicebiz.wx.entity.WXSecretEntity;
import by.future.servicebiz.wx.entity.WeiXinEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Zeal on 2017/5/24.
 */
public class WeiXinUtils {

    private static Logger logger = LoggerFactory.getLogger(WeiXinUtils.class);

    /**
     * 根据code获取openId和网页授权接口凭证access_token
     *
     * @author by@Deng
     * @date 2017/11/8 下午2:21
     */
    public static String getOpenIdByCode(String code,String appid,String secret){

        JSONObject parseObject = getOpenIdAccessTokenByCode(code,appid,secret);

        //根据code获取access_token和openId的JSON字符串
        return parseObject.getString("openid");
    }


    /**
     * 根据code获取openId或者授权页面的access_token(非中枢access_token)
     * param: openId或者access_token或者refresh_token
     *
     * @Author: by@Deng
     * @Date: 2018/4/28 上午11:00
     */
    private static JSONObject getOpenIdAccessTokenByCode(String code, String appid, String secret){

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String sendParam = "appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";

        String openIdAccessToken = HttpClientUtils.sendGet(url,sendParam);
        JSONObject parseObject = JSON.parseObject(openIdAccessToken);

        //判断是否请求成功，成功不会有errcode字段
        String errCode = parseObject.getString("errcode");

        //获取openId不成功
        if(StringUtils.isEmpty(errCode)){
            return parseObject;
        }else{
            logger.error("code有问题无法生成新的openId");
            return null;
        }
    }


    /**
     * 根据小程序appid和secret以及code来获取openid
     *
     * @Author: by@Deng
     * @Date: 2018/4/28 下午12:20
     */
    public static JSONObject getWXOpenId(String code, String appid, String appSecret){
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String param = "appid=" + appid + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        //根据code获取access_token和openId的JSON字符串
        return HttpClientUtils.sendPostJson(url, param);
    }


    /**
     * 获取微信公众号和小程序的access_token值和失效时间
     * @author by@Deng
     * @date 2017/11/8 下午2:39
     */
    public static WXSecretEntity getAccessToken(String appId, String appSecret){
        Date loseTime = new Date((System.currentTimeMillis() + Const.LOSETIME));  //失效时间

        //访问accessToken接口地址
        String url="https://api.weixin.qq.com/cgi-bin/token";
        String param = "grant_type=client_credential&appid="+appId+"&secret="+appSecret;

        //1.获取accessToken字符串
        String retValue = HttpClientUtils.sendGet(url,param);
        JSONObject accessTokenObj = JSON.parseObject(retValue);

        //2.获得access_token值
        String accessToken = accessTokenObj.getString("access_token");

        WXSecretEntity wxSecretEntity = new WXSecretEntity();
        wxSecretEntity.setLoseTime(loseTime);
        wxSecretEntity.setAccessToken(accessToken);

        return wxSecretEntity;
    }


    /**
     * 根据access_token获得jsapi_ticket值（js接口访问凭证）
     * @author by@Deng
     * @date 2017/11/8 下午4:15
     */
    public static String getTicket(String access_token){

        String url1 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        String param1 ="access_token="+access_token+"&type=jsapi";

        //1.获得jsapi_ticket字符串JSON
        String jsapi=HttpClientUtils.sendPost(url1,param1);
        JSONObject jsapiObj = JSON.parseObject(jsapi);

        //2.得到jsapi_ticket值
        String jsapi_ticket = jsapiObj.getString("ticket");
        return jsapi_ticket;
    }


    /**
     * 获取二维码短连接
     * @author by@Deng
     * @date 2017/11/8 下午2:40
     */
    public static JSONObject getShortUrl(String url, WeiXinEntity weiXinEntity) throws Exception {
        //访问接口
        String access_token=weiXinEntity.getAccessToken();
        String requestUrl="https://api.weixin.qq.com/cgi-bin/shorturl?access_token="+access_token;
        //获取参数
        String param="{\"action\":\"long2short\",\"long_url\":\""+url+"\"}";
        JSONObject res = HttpClientUtils.sendPostJson(requestUrl,param);
        return res;
    }


    /**
     * 创建统一下单发送参数,获取prepay_id值
     * @author by@Deng
     * @date 2017/11/9 上午9:51
     */
    public static String getPrepayId(WXPayInfoEntity payInfoEntity, WXSecretEntity wxSecretEntity){
        try {

            payInfoEntity.setNotify_url(Const.NOTIFY_URL);//回调地址为了处理业务
            payInfoEntity.setDevice_info("WEB");    //小程序、公众号相同
            payInfoEntity.setTrade_type("JSAPI");   //小程序、公众号相同
            payInfoEntity.setNonce_str(UUIDUtils.getUUID());   //随机串
            payInfoEntity.setAppid(wxSecretEntity.getAppId());  //公众号或者小程序的appId
            payInfoEntity.setMch_id(wxSecretEntity.getMchId()); //公众号或者小程序商家账户
            payInfoEntity.setAttach(wxSecretEntity.getAttach()); //附加数据
            payInfoEntity.setBody(wxSecretEntity.getBody()); //附加消息

            //签名运算
            String payStr= MapUtils.formatObject(payInfoEntity,false);
            String signTemp =payStr+"&key="+wxSecretEntity.getPayKey(); //这个key注意

            //将signTemp进行md5加密
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(signTemp.getBytes("UTF-8"));
//            String sign = CommonUtil.byteToStr(md5.digest()).toUpperCase(); //最终签名
            String sign = null; //最终签名

            payInfoEntity.setSign(sign);
            String objectXml = XMLUtils.objectToXML(payInfoEntity); //将payInfoEntity转成xml形式的订单发送参数


            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";  //微信统一下单接口
            String wxRetXml = HttpClientUtils.sendPost(url,objectXml);  //访问微信返回信息
            Map<String,String>  map = XMLUtils.parseXml(wxRetXml);

            String prepay_id = map.get("prepay_id");

            return prepay_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取微信支付paySign最终签名
     * @author by@Deng
     * @date 2017/11/9 下午3:02
     */
    public static String getOrderPaySign(WeiXinEntity weiXinEntity,WXSecretEntity wxSecretEntity){

        //获取生成随机数
        weiXinEntity.setNonceStr(UUIDUtils.getUUID());
        //生成时间戳
        weiXinEntity.setTimeStampOn(Long.toString(System.currentTimeMillis() / 1000));
        try {
            String paySignTemp = "appId="+wxSecretEntity.getAppId()
                    + "&nonceStr=" + weiXinEntity.getNonceStr()
                    + "&package=prepay_id=" + weiXinEntity.getPrepayId()
                    + "&signType=MD5&timeStamp=" + weiXinEntity.getTimeStampOn()
                    + "&key="+wxSecretEntity.getPayKey();

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(paySignTemp.getBytes("UTF-8"));
//            String paySign = CommonUtil.byteToStr(md5.digest()).toUpperCase();
            String paySign = null;

            return paySign;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 服务于signature
     * @author by@Deng
     * @date 2017/11/9 下午3:32
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    /**
     * 获取微信公众号授权支付的signature
     * @author by@Deng
     * @date 2017/11/9 下午3:30
     */
    public static WeiXinEntity getSignature(WeiXinEntity weiXinEntity){
        String nonce_str= UUIDUtils.getUUID(); //随机串
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);    //时间戳
        String signature = null;
        String str="";

        //1.将参数排序并拼接字符串
        str = "jsapi_ticket=" + weiXinEntity.getWxJsapiTicket() +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + weiXinEntity.getUrl();

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        weiXinEntity.setNonceStr(nonce_str);
        weiXinEntity.setTimeStampOn(timestamp);
        weiXinEntity.setSignature(signature);

        return weiXinEntity;
    }


    /**
     * 查找小程序的个人自定义模板
     * @author by@Deng
     * @date 2017/11/14 下午7:51
     */
    public static List<JSONObject> findXCXTemplate(String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/wxopen/template/list?access_token="+accessToken;
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("offset",0);  //从第1条开始加载
        jsonParam.put("count",5);   //一次性加载5条

//        JSONObject retJson = HttpClientUtils.postJSON(url,jsonParam);
        JSONObject retJson = null;
        if(Integer.valueOf(retJson.get("errcode").toString())==0){
            return (List<JSONObject>) retJson.get("list");
        }
        return null;
    }



    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] {Const.TOKEN, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        if(null == arr || arr.length == 0){
            return false;
        }
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("加密排序后的字符串："+tmpStr);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }


    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
