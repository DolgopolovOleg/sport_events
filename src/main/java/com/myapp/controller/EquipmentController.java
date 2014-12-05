package com.myapp.controller;


import com.myapp.entity.Equipment;
import com.myapp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllEquipments(Model model){
        String page = "";
        List<Equipment> equipments = equipmentService.findAll();
        model.addAttribute("equipments", equipments);
        page = "equipments";
        return page;
    }

}
