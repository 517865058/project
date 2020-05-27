package rjzx.spboot.hzu.project.service;

public interface MailService {

     /**
      * 发送用户注册账号激活邮件
      * @param to
      * @param subject
      * @param content
      */
     void sendRegisterEmail(String to, String subject, String content);

}
