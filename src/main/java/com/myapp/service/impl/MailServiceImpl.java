package com.myapp.service.impl;

import com.myapp.entity.Activation;
import com.myapp.entity.User;
import com.myapp.mail_utils.MessagePropertiesCompiler;
import com.myapp.service.MailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;


@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    private String prefix = "mail_tmpl/";
    private String extension = ".vm";

    @Override
    public void sendUserMail(String to, User user){
        SimpleMailMessage message = new SimpleMailMessage();
        String userName = user.getUsername();
        message.setFrom("noreply@sportgrounds.com");
        message.setTo(to);
        message.setSubject("First letter");
        message.setText("Some message");
        mailSender.send(message);
    }

    @Override
    public void sendUserRegistrationMail(Activation activation) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("33druga@gmil.com");
//            helper.setTo("allegamex@gmail.com");
            User user = activation.getUser();
            if(user.getUsername()==null)
                return;
            helper.setTo(user.getUsername());
            helper.setSubject("Thank you for registration.");

            Map<String, Object> properties = MessagePropertiesCompiler.compileUserRegistationProperties(activation);
            String template = this.prefix + "user_registration" + this.extension;
            String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", properties);
            helper.setText(emailText, true);
            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
