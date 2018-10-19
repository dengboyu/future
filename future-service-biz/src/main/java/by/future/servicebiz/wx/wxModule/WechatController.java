package by.future.servicebiz.wx.wxModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class WechatController {

    @Autowired
    private IWechatBiz wechatBiz;

    @RequestMapping(value = "valid/wechat", method = RequestMethod.GET)
    public String wechatValid(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {

            return wechatBiz.wechatOfficalAccountValidate(request, null, hashMap);
        } catch (Exception e) {

        }
        return "";
    }

    @RequestMapping(value = "event/wechat", method = RequestMethod.POST)
    public String wechatEvent(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {

            request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
            response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
            return wechatBiz.wechatOfficalAccountEvent(request, "", hashMap);
        } catch (Exception e) {

        }
        return "";
    }


    /**
     * 获取code值
     *
     * @Author：by@Deng
     * @Date：2018/5/16 17:58
     */
    @RequestMapping(value = "/redirectAuth", method = RequestMethod.GET)
    public void redirectAuth(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            //跳转到授权页面
            wechatBiz.redirectAuth(request, response, hashMap);
        } catch (Exception e) {

        }
    }


    /**
     * 根据code获取openId
     *
     * @Author：by@Deng
     * @Date：2018/5/16 18:01
     */
    @GetMapping("/getOpenId")
    public void getOpenIdByCode(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            wechatBiz.getOpenIdByCode(request, response, hashMap);
        } catch (Exception e) {

        }
    }


    /**
     * 根据code获取openId以及用户信息
     *
     * @Author：by@Deng
     * @Date：2018/5/16 18:01
     */
    @GetMapping("/getUserInfoByCode")
    public void getUserInfoByCode(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {

            wechatBiz.getUserInfoByCode(request, response, hashMap);
        } catch (Exception e) {

        }
    }


    @RequestMapping(value = "/getScanSourceWxLogin", method = RequestMethod.GET)
    public void getScanSourceWxLogin(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            wechatBiz.getScanSourceWxLogin(request, response, hashMap);
        } catch (Exception e) {

        }
    }


}
