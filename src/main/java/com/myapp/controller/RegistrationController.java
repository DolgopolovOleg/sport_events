package com.myapp.controller;


import com.myapp.entity.Activation;
import com.myapp.entity.User;
import com.myapp.service.AuthService;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String createUser(Model model){
        model.addAttribute(new User());
        return "users_registration";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String addUserFromForm(@Valid User user, BindingResult bindingResult){
        //TODO: check email
        if(bindingResult.hasErrors()){
            return "users_registration";
        }
        userService.createNewUser(user);
        return "redirect:/activation?userID=" + user.get_id();
    }

    @RequestMapping(value = "activation", method = RequestMethod.GET)
    public String getUserActivationPage(@RequestParam("userID") Integer userID,
                                 Model model){
        User user = userService.findById(userID);

        if(user.getEnabled()) return "redirect:/users/" + user.get_id();

        model.addAttribute("user", user);
        return "users_activation";
    }

    @RequestMapping(value = "activateUser", method = RequestMethod.GET, params = {"activationCode"})
    public String userActivation(@RequestParam("userID") Integer userID,
                                 @RequestParam("activationCode") String activationCode,
                                 Model model){
        //TODO check for activate. If user follow the link one else

        User user = userService.findById(userID);

        if(user.getEnabled()) return "redirect:/users/" + user.get_id();

        Activation activation = authService.findByUserID(userID);
        Boolean isActivationValid = this.checkActivateCode(activation, activationCode);
        model.addAttribute("user", user);
        String page = "redirect:";
        if(isActivationValid){
            userService.activateUser(user);
            page += "/users/" + user.get_id();
        }else{
            page += "/activation?userID=" + user.get_id();
        }
        return page;
    }

    private Boolean checkActivateCode(Activation activation, String activationCode) {
        if( activation.getActivationCode().equals(activationCode.trim()) ){
            User user = activation.getUser();
//            user.setEnabled(true);
            userService.save(user);
            return true;
        }
        return false;
    }


}