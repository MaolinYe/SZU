package org.example;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Main {
    public static void main(String[] args) {
        try{
            Email email=new SimpleEmail();
            String username="2351925363@qq.com";
            String receiver="2351925363@qq.com";
            String password="veqfxtxhupwkdjbe";
            String subject="编程练习4";
            String message="这是2021155015-叶茂林自编写邮件发送程序，从邮箱2351925363@qq.com发送的邮件，请查收";
            email.setHostName("smtp.qq.com");
            email.setSmtpPort(587);
            email.setAuthentication(username,password);
            email.setFrom(username);
            email.addTo(receiver);
            email.setSubject(subject);
            email.setMsg(message);
            email.send();
        }catch (EmailException e){
            e.printStackTrace();
        }
    }
}