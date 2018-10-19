package by.future.common.utils;

import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.soap.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service调用
 *
 * @author by@Deng
 * @create 2018-07-15 23:15
 */
public class SoapUtils {


    /**
     * 调用远程web-service
     *
     * @Author: by@Deng
     * @Date: 2018/7/15 下午11:16
     */
    public static void testService() {
        //git

        String endpoint = "endpoint";
        Service service = new Service();
        Call call;
        try {
            call = service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName(endpoint, "addSms"));

            //String param = new String("安迪".getBytes(),"ISO-8859-1");//如果没有加这段，中文参数将会乱码

            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("sms_to", "18322695263");
            paramMap.put("content", "1231");
            paramMap.put("status", "1");
            paramMap.put("sms_type", "21");
            paramMap.put("sms_agent", "chuangshi");

            //String param = new String("中文");
            String s = (String) call.invoke(new Object[]{paramMap});
            s = new String(s.getBytes("UTF-8"));//如果没有转换编码，中文也会乱码

            Map<String, String> map = XMLUtils.parseXml(s);
            System.out.println(map.get("result"));

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 远程调用web-service  待测试
     *
     * @Author: by@Deng
     * @Date: 2018/7/15 下午11:17
     */
    public static void testSOAP(){
        SOAPConnection soapConnection = null;
        try{
            //建立连接
            soapConnection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

            //设置调用的webservice方法，及传参
            SOAPBody body = soapEnvelope.getBody();
            SOAPElement element = body.addChildElement(soapEnvelope.createName("addSms"));

            element.addChildElement("sms_to").addTextNode("18322695263");
            element.addChildElement("content").addTextNode("1231");
            element.addChildElement("sms_type").addTextNode("21");
            element.addChildElement("sms_agent").addTextNode("chuangshi");

            soapMessage.saveChanges();

            //获取返回值
            String url = "url";
            SOAPMessage reply = soapConnection.call(soapMessage,url);
            soapPart = reply.getSOAPPart();
            soapEnvelope = soapPart.getEnvelope();
            body = soapEnvelope.getBody();
            Node returnValue = (Node) body.getChildElements().next();

            if (returnValue != null) {
                if (returnValue.getChildNodes().item(0).getNodeName().equals("return")) {
                    List<HashMap<String,String>> ReturnArray = new ArrayList<HashMap<String,String>>();
                    for (int i=0;i<returnValue.getChildNodes().item(0).getChildNodes().getLength();i++) {
                        String key = returnValue.getChildNodes().item(0).getChildNodes().item(i).getNodeName();
                        String value = returnValue.getChildNodes().item(0).getChildNodes().item(i).getNodeValue();
                        System.out.println(key+":"+value);
                    }
                }
            } else {
                System.out.println("nothing returned");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try {
                if(soapConnection!=null)soapConnection.close();
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }
    }



}
