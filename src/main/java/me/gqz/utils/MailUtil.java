package me.gqz.utils;

import io.github.biezhi.ome.OhMyEmail;
import lombok.extern.slf4j.Slf4j;
import javax.mail.MessagingException;
import java.io.*;
import java.util.Properties;

/**
 * <p>Title: MailUtil. </p>
 * <p>Description 发送邮件工具类 </p>
 * @author dragon
 * @date 2018/7/9 下午3:59
 */
@Slf4j
public class MailUtil {

    /**
     * <p>Title: SMTP_SELF. </p>
     * <p>自定义邮件smtp-阿里 </p>
     * @param debug
     * @author dragon
     * @date 2018/7/9 下午4:25
     * @return Properties
     */
    public static Properties SMTP_SELF(Boolean debug) {
        Properties props = OhMyEmail.defaultConfig(debug);
        props.put("mail.smtp.host", "smtp.mxhichina.com");
        return props;
    }

    /**
     * <p>Title: sendMail. </p>
     * <p>发送邮件 </p>
     * @param
     * @author dragon
     * @date 2018/7/9 下午4:20
     */
    public static void sendMail(String toMail, String nickName, String loginName, String loginPwd) {
        try {
            Properties properties = new Properties();
            InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("cfg.properties");
            properties.load(in);
            String mailUrl = properties.getProperty("mail.url");
            String mailPwd = properties.getProperty("mail.pwd");
            // 注意，此处密码为POP3/SMTP服务授权码
            OhMyEmail.config(OhMyEmail.SMTP_163(true), mailUrl, mailPwd);
            OhMyEmail.subject("欢迎加入高秋梓资源组！").from("高秋梓资源组").to(toMail)
                    .text("欢迎" + nickName + "加入资源组！\n您的登录名为【" + loginName + "】 系统自动生成您的密码为【" + loginPwd + "】 请您妥善保管。").send();
        } catch (MessagingException e ) {
            log.error("发送邮件失败 = {}", e);
        } catch (FileNotFoundException e) {
            log.error("配置邮件失败 = {}", e);
        } catch (IOException e) {
            log.error("配置邮件失败 = {}", e);
        }
    }
}
