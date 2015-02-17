package com.myapp.controller;

import com.myapp.entity.User;
import com.myapp.mail_utils.MessageProducer;
import com.myapp.service.MailService;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jms.JMSException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageProducer messageProducer;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showUsers(Model model) throws JMSException {
        List<User> allUser = userService.findAll();
        model.addAttribute("title", "Users");
        model.addAttribute("users", allUser);
//        MessageProducerImpl mp = new MessageProducerImpl();
//        mp.sendMessages();

        return "users";
    }

    @RequestMapping(value = "/{userIDorUserNickname}", method = RequestMethod.GET)
    public String showUser(
            @PathVariable String userIDorUserNickname,
            Model model){
//        TODO: id check (if not an Integer or do not exist User)
        model.addAttribute("title", "Users");
        User user = userService.findById(new Integer(userIDorUserNickname));
        model.addAttribute("user", user);
//        messageProducer.sendMessages(user);
//        mailService.sendUserMail("allegamex@gmail.com", user);
        return "users_info";
    }

}
