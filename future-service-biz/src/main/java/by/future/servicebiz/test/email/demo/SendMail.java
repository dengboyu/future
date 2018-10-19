package by.future.servicebiz.test.email.demo;

/*import com.bypro.future.common.exception.ByException;
import com.bypro.future.module.user.entity.SysUserEntity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

*//**
 * 发送邮件
 *
 * @author by@Deng
 * @create 2017-10-17 08:14
 *//*
public class SendMail   implements Runnable {

    private SysUserEntity user ;

    public SendMail(SysUserEntity user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host","smtp.163.com");
            properties.setProperty("mail.transport.protocol","smtp");
            properties.setProperty("mail.smtp.auth","true");

            Session session = Session.getInstance(properties);
            Transport ts = session.getTransport();   //传输对象
            ts.connect(session.getProperty("smtp.163.com"),"by6886432","11111111"); //第三方密码

            Message message = makeMessage(session,user);    //发送邮件内容
            ts.sendMessage(message,message.getAllRecipients());  //发送邮件
            ts.close();

        } catch (Exception e) {
            throw new ByException("发送邮件失败");
        }
    }


    public Message makeMessage(Session session,SysUserEntity user) throws Exception {
        MimeMessage message = new MimeMessage(session);

        //1.设置邮件的基本信息
        message.setFrom(new InternetAddress("by6886432@163.com"));  //发送人
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});    //接收人
        message.setSubject("用户注册邮件"); //主题

        String info = "恭喜您，注册成功";
        message.setContent(info,"text/html;charset=utf-8");

        return null;
    }



}*/
