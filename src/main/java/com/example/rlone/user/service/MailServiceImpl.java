package com.example.rlone.user.service;

import com.example.rlone.util.ServiceResult;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class MailServiceImpl {

  public ServiceResult sendMessage() throws Exception {
    Properties prop = new Properties();
    prop.setProperty("mail.host", "smtp.qq.com");
    prop.setProperty("mail.transport.protocol", "smtp");
    prop.setProperty("mail.smtp.auth", "true");

    // 使用JavaMail发送邮件的5个步骤
    // 1、创建session
    Session session = Session.getInstance(prop);
    // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
    session.setDebug(true);
    // 2、通过session得到transport对象
    Transport ts = session.getTransport();
    // 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
    ts.connect("smtp.qq.com", "syjsss@qq.com", "sbxtnmiqtrqzbgec");
    // 4、创建邮件
    Message message = createSimpleMail(session);
    // 5、发送邮件
      ts.sendMessage(message, message.getAllRecipients());
      ts.close();
    ServiceResult result = new ServiceResult(false);
    result.setSuccess(true);
    result.setData(message.getSubject());
    return result;

  }

  public static MimeMessage createSimpleMail(Session session) throws Exception {
    // 创建邮件对象
    MimeMessage message = new MimeMessage(session);
    // 指明邮件的发件人
    message.setFrom(new InternetAddress("syjsss@qq.com"));
    // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
    message.setRecipient(Message.RecipientType.TO, new InternetAddress("1981908210@qq.com"));
    // 邮件的标题
    message.setSubject("第一封邮件");
    // 邮件的文本内容
    message.setContent("你是张传进", "text/html;charset=UTF-8");
    // 返回创建好的邮件对象
    return message;
  }
}
