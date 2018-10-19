package by.future.servicebiz.test.email.demo;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * mime邮件
 *
 * @author by@Deng
 * @create 2017-10-16 08:09
 */
public class MailDemo {

    public void sendMessage() throws Exception {

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host","smtp.163.com");
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","true");

        Session session = Session.getInstance(properties);
        Transport ts = session.getTransport();   //传输对象
        ts.connect(session.getProperty("smtp.163.com"),"by6886432","111111"); //第三方密码


        MimeMessage message = new MimeMessage(session);

        //1.设置邮件的基本信息
        message.setFrom(new InternetAddress("by6886432@163.com"));  //发送人
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("by6886432@163.com")});    //接收人
        message.setSubject("来自北方的狼的一封信"); //主题

        //2.准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封来自北方的狼的信<br/><img src='cid:xxx'><br/>请您签收","text/html;charset=UTF-8");

        //3.准备附件数据

        //图片插入正文里
        MimeBodyPart img = new MimeBodyPart();
        DataHandler imgHandler =  new DataHandler(new FileDataSource("/Users/by/code/myProject/future/src/main/webapp/WEB-INF/download/背景.png"));
        img.setDataHandler(imgHandler);
        img.setContentID("xxx");

        //附件1-图片处理器
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler attachHandler = new DataHandler(new FileDataSource("/Users/by/code/myProject/future/src/main/webapp/WEB-INF/download/背景.png"));
        attach.setDataHandler(attachHandler);
        attach.setFileName(MimeUtility.decodeText(attachHandler.getName())); //附件名称1

        //附件2
        MimeBodyPart attach2 = new MimeBodyPart();
        DataHandler attachHandler2 = new DataHandler(new FileDataSource("/Users/by/code/myProject/future/src/main/webapp/WEB-INF/download/门票信息.json"));
        attach2.setDataHandler(attachHandler2);
        attach2.setFileName(MimeUtility.decodeText(attachHandler2.getName())); //附件名称2


        //4.描述文件关系
        //A.正文和图片关系--C
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(img);
        mp1.setSubType("related");

        //再把C封装成一个MimeBodyPart--D(新的正文)
        MimeBodyPart mimeText = new MimeBodyPart();
        mimeText.setContent(mp1);

        //E.正文和附件关系
        MimeMultipart mp2 = new MimeMultipart();
        mp2.addBodyPart(mimeText);
        mp2.addBodyPart(attach);
        mp2.addBodyPart(attach2);
        mp2.setSubType("mixed");

        //5.交给邮件处理
//        message.setContent(mp1);  //图片
        message.setContent(mp2);    //附件
        message.saveChanges();

//        message.writeTo(new FileOutputStream("/Users/by/Desktop/plane.eml"));
        ts.sendMessage(message,message.getAllRecipients());    //发送邮件
        ts.close();


    }



}
