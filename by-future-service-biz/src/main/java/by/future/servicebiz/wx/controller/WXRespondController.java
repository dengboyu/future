package by.future.servicebiz.wx.controller;

import by.future.common.utils.XMLUtils;
import by.future.servicebiz.wx.common.WeiXinUtils;
import by.future.servicebiz.wx.entity.WeiXinIndexEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 响应微信请求自动应答
 *
 * @author by@Deng
 * @create 2017-09-20 10:39
 */
@RestController
public class WXRespondController {

//    public Logger logger = Logger.getLogger(WXRespondController.class);

    /**
     * 微信回调验证
     *
     * @Author: by@Deng
     * @Date: 2018/4/28 上午10:18
     */
    @RequestMapping(value="setOfficialIndex")
    public String setOfficialIndex(WeiXinIndexEntity wc){

        String signature = wc.getSignature(); // 微信加密签名
        String timestamp = wc.getTimestamp(); // 时间戳
        String nonce = wc.getNonce();// 随机数
        String echostr = wc.getEchostr();// 随机字符串

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WeiXinUtils.checkSignature(signature, timestamp, nonce)) {
//            logger.info("应答成功");
            return echostr;
        } else {
            System.out.println("不是微信服务器发来的请求,请小心!");
            return null;
        }
    }



    /**
     * 生成首页格式使用
     *
     * @Author: by@Deng
     * @Date: 2018/4/28 上午10:18
     */
    @RequestMapping(value="setWeiXinIndex")
    public void setWeiXinIndex(HttpServletResponse res) throws Exception{
        String jsonString = "";

        res.getWriter().write(jsonString);
    }

    /**
     * 微信返回支付接口
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "weiXinReturn")
    public String weiXinReturn(HttpServletRequest request) throws Exception {

        String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";

        try {
            InputStream inStream = request.getInputStream();

            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
//            logger.error("微信支付----付款成功----");
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息

//            logger.error("微信支付----result----=" + result);
            Map<Object, Object> map = XMLUtils.getXML(result);

            if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
//                logger.error("微信支付----返回成功");

                // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

                // 处理业务 -修改订单支付状态
//                logger.error("微信支付回调：修改的订单=" + map.get("out_trade_no"));

                String orderNum = (String) map.get("out_trade_no");
                String orderId =null;
                if(orderNum.startsWith("Tstc")){

                }else if(orderNum.startsWith("2")){
                    //路线订单
                    orderId=orderNum.substring(1,orderNum.length());
                }else if(orderNum.startsWith("xcx")){
                    //小程序订单
                    orderId=orderNum.substring(3,orderNum.length());
                }else if(orderNum.startsWith("fxsxcx")){
                    //小程序订单
                    orderId=orderNum.substring(6,orderNum.length());
                }

            }else {
//                logger.info("支付失败,错误信息：" + map.get("err_code"));
            }
        } catch(IOException e) {
//            logger.error("支付回调发布异常：" + e);
            e.printStackTrace();
        }
        return resXml;
    }



}
