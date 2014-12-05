package com.myapp.controller;

import com.myapp.entity.Sport;
import com.myapp.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sports")
public class SportController {

    @Autowired
    SportService sportService;

    @RequestMapping(value="")
    public String showAllSports(Model model){
        List<Sport> sports = sportService.findAll();
        model.addAttribute("sports", sports);
        return "sports";
    }

}
