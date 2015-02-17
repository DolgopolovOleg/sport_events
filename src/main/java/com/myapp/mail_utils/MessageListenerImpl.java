//package com.myapp.mail_utils;
//
//
//import com.myapp.service.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import javax.annotation.Resource;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.TextMessage;
//
//public class MessageListenerImpl implements javax.jms.MessageListener{
//
//    @Resource(name = "mailService")
//    private MailService mailService;
//
//    @Override
//    public void onMessage(Message message) {
//        System.out.println("We are in LISTENER");
//        TextMessage msg = (TextMessage) message;
//        try{
//            System.out.println("Message: " + msg.getText());
//            mailService.sendSimpleMail();
//        }catch(JMSException e){
//            e.printStackTrace();
//        }
//    }
//}
