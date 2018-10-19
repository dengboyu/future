package by.future.servicebiz.wx.wxModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


public interface IWechatBiz {
    String wechatOfficalAccountValidate(HttpServletRequest request, String partner, HashMap<String, String> hashMap);

    String wechatOfficalAccountEvent(HttpServletRequest request, String partner, HashMap<String, String> hashMap);

    void redirectAuth(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap);

    void getOpenIdByCode(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap);

    void getUserInfoByCode(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap);

    void getScanSourceWxLogin(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> hashMap);

    String getAutoReply(String key, String partner);
}
