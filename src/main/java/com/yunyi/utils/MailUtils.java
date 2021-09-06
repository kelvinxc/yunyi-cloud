package com.yunyi.utils;

import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailUtils {

    //邮件发送器
    private JavaMailSenderImpl mailSender;
    Logger logger = LogUtils.getInstance(MailUtils.class);

    public MailUtils(JavaMailSenderImpl mailSender){
        this.mailSender = mailSender;
    }

    public String sendCode(String email,String userName,String password){
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        logger.info("开始发送复杂邮件...");
        logger.info("mailSender对象为:"+mailSender);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("云翼网盘-邮箱验证");
            helper.setText("<h2 >云翼网盘-简洁、优雅、免费</h2>" +
                    "<h3>用户注册-邮箱验证<h3/>" +
                    "您现在正在注册云翼网盘账号<br>" +
                    "验证码: <span style='color : red'>"+code+"</span><br>" +
                    "用户名 : "+userName+
                    "<br>密码 : "+password+
                    "<hr>"+
                    "<h5 style='color : red'>如果并非本人操作,请忽略本邮件</h5>",true);
            helper.setFrom("2363183105@qq.com");
            helper.setTo(email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("mimeMessage对象为:"+mimeMessage);
        mailSender.send(mimeMessage);
        return String.valueOf(code);
    }
}
