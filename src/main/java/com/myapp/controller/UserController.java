package com.myapp.controller;

import com.myapp.entity.User;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showUsers(Model model){
        List<User> allUser = userService.findAll();
        model.addAttribute("title", "Users");
        model.addAttribute("users", allUser);
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
        return "user_info";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createUser(Model model){
        model.addAttribute(new User());
        return "users_create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String addUserFromForm(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users_create";
        }
        userService.save(user);
        return "redirect:/users/" + user.get_id();
    }
}
