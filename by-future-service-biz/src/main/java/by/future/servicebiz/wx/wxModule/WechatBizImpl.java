package by.future.servicebiz.wx.wxModule;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Component
public class WechatBizImpl implements IWechatBiz {
    /**
     * 验证公众号服务器
     *
     * @param request
     * @param hashMap
     * @return
     */
    @Override
    public String wechatOfficalAccountValidate(HttpServletRequest request, String partner, HashMap<String, String> hashMap) {
        try {
            String signature = request.getParameter("signature");
            String nonce = request.getParameter("nonce");
            String timestamp = request.getParameter("timestamp");
            String echoStr = request.getParameter("echostr");
//            if (CheckUtil.checkSignature(timestamp, nonce, signature, WechatCommon.GetToken(partnerType))) {

//                return echoStr;
//            } else {

//            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 获取微信公众号事件
     *
     * @param request
     * @param hashMap
     * @return
     */
    @Override
    public String wechatOfficalAccountEvent(HttpServletRequest request, String partner, HashMap<String, String> hashMap) {
        try {
            String signature = request.getParameter("signature");
            String nonce = request.getParameter("nonce");
            String timestamp = request.getParameter("timestamp");

        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 跳转到授权页面
     *
     * @param request
     * @param response
     * @param hashMap
     */
    @Override
    public void redirectAuth(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap) {
        try {
            String redirect = request.getParameter("redirect");
            String channel = request.getParameter("channel");
            String scope = "snsapi_base";

            String redirect_uri = "http://www.xxx.com/api/" + redirect + "?channel=" + channel;

            //兼容公众号支付获取openId--真实跳转的目录
            String redirectRealUrl = request.getParameter("redirectRealUrl");
            if (StringUtils.isEmpty(redirectRealUrl)) {
                redirectRealUrl = "weixin";
            }

            String authUrl = "";
            if (!StringUtils.isEmpty(request.getParameter("scope"))) {
                scope = "snsapi_userinfo";
            }
            authUrl += "&redirect_uri=" + URLEncoder.encode(redirect_uri, "utf-8") + "&response_type=code&scope=" + scope + "&state=" + redirectRealUrl + "#wechat_redirect";

            response.sendRedirect(authUrl);
        } catch (Exception e) {

        }
    }

    /**
     * 根据code获取openId
     *
     * @Author：by@Deng
     * @Date：2018/5/16 18:07
     */
    @Override
    public void getOpenIdByCode(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap) {

        String logTitle = "getOpenId";

        try {
            String code = request.getParameter("code");
            String channel = request.getParameter("channel");
            String state = request.getParameter("state"); //要跳转的真实的url
            String authUrl = "";

            Map<String, String> retMap = getOpenIdByCode(code, "snsapi_base", channel, hashMap);
            String openId = retMap.get("openId");

            authUrl = state + "?channel=" + channel + "&openId=" + openId;

            response.sendRedirect(authUrl);
        } catch (Exception e) {

        }

    }


    /**
     * 根据code获取openId以及用户信息
     *
     * @Author：by@Deng
     * @Date：2018/5/16 18:01
     */
    @Override
    public void getUserInfoByCode(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap) {

        String logTitle = "getUserInfoByCode";

        try {
            String code = request.getParameter("code");
            String channel = request.getParameter("channel");
            String state = request.getParameter("state"); //要跳转的真实的url
            String authUrl = "";

            Map<String, String> retMap = getOpenIdByCode(code, "snsapi_userinfo", channel, hashMap);
            String openId = retMap.get("openid");

            String headimgurl = "";
            String nickname = "";
            if (retMap.get("headimgurl") != null) {
                headimgurl = URLEncoder.encode(retMap.get("headimgurl"), "utf-8");
            }
            if (retMap.get("nickname") != null) {
                nickname = URLEncoder.encode(retMap.get("nickname"), "utf-8");
            }

            String mark = "?";
            if (state.contains("?")) {
                mark = "&";
            }
            authUrl = state + mark + "channel=" + channel + "&openId=" + openId + "&headImg=" + headimgurl + "&nickName=" + nickname;


            response.sendRedirect(authUrl);
        } catch (Exception e) {

        }

    }


    /**
     * 授权登录微信登录帐号
     *
     * @param request
     * @param response
     * @param hashMap
     */
    @Override
    public void getScanSourceWxLogin(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap) {
        try {
            String channel = StringEscapeUtils.escapeHtml3(request.getParameter("channel"));
            String code = request.getParameter("code");
            String scope_type = "snsapi_base";
            Map<String, String> map = getOpenIdByCode(code, scope_type, channel, hashMap);
            String openId = "";
            if (map != null) {
                openId = map.get("openid");
            }
            String url ="url";
            response.sendRedirect(url);
        } catch (Exception e) {

        }
    }


    /**
     * 根据key从cookie里面获取值
     *
     * @param cookies
     * @param key
     * @return
     */
    private String getValue(Cookie[] cookies, String key) {
        if (cookies == null || cookies.length == 0) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return "";
    }


    /**
     * 获取授权用户信息
     *
     * @param code
     * @param scope_type
     * @param channel
     * @param hashMap
     * @return
     */
    private Map<String, String> getOpenIdByCode(String code, String scope_type, String channel, HashMap<String, String> hashMap) {
        String url;

//        Map<String, String> map = JSON.parseObject(QRCodeUtil.sendHttpGet(url, true, hashMap), new TypeReference<Map<String, String>>() {
//        });
//        if ("snsapi_base".equals(scope_type)) {
//            return map;
//        } else {
//            String access_token = getUserAccessToken(map, channel, hashMap);
//            String userInfoUrl = WechatCommon.WEIXIN_USERINFO + access_token + "&openid=" + map.get("openid") + "&lang=zh_CN";
//            map = JSON.parseObject(QRCodeUtil.sendHttpGet(userInfoUrl, true, hashMap), new TypeReference<Map<String, String>>() {
//            });
//        }
        return new HashMap<>();
    }


    /**
     * 验证access_token是否有效，如果无效，调用refresh_token进行刷新
     *
     * @param map
     * @param channel
     * @param hashMap
     * @return
     */
    private String getUserAccessToken(Map<String, String> map, String channel, HashMap<String, String> hashMap) {
        String checkValidUrl = WechatCommon.WEIXIN_CHECK_ACCESS_TOKEN_VALID + map.get("access_token") + "&openid=" + map.get("openid");

        return "";
    }


    /**
     * 获取用户基本信息
     *
     * @param openId
     * @param channel
     * @param hashMap
     * @return
     */
    private Map<String, String> getUserInfo(String openId, String channel, HashMap<String, String> hashMap) {
        String access_token = "";
        String url = WechatCommon.WEIXIN_BASE_USERINFO + access_token + "&openid=" + openId + "&lang=zh_CN'";
//        return JSON.parseObject(QRCodeUtil.sendHttpGet(url, true, hashMap), new TypeReference<Map<String, String>>() {
//        });
        return new HashMap<>();
    }


    /**
     * 微信自动回复
     *
     * @Author:cy@Fan
     * @Date:2018/6/25 15:01
     */
    public String getAutoReply(String key, String partner) {
        HashMap<String, String> logMap = new HashMap<>();
        logMap.put("key", key);
        final String logTitle = "wechatAutoReply";

        return logTitle;
    }
}
