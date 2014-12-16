package com.myapp.controller;


import com.myapp.entity.Place;
import com.myapp.service.EquipmentService;
import com.myapp.service.PlaceService;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    UserService userService;

    @Autowired
    EquipmentService equipmentService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String showAllPlaces(Model model){
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
//        com.myapp.entity.extended.PlaceView plV = placeService.getPlaceViewByPlaceId(1);
//        placeService.findEquipmentsForPlace(placeService.findById(1));
        return "places";
    }

    @RequestMapping(value = "/{placeID}", method = RequestMethod.GET)
    public String showPlace(
            @PathVariable String placeID,
            Model model){
//        TODO: id check (if not an Integer or do not exist Place)
        model.addAttribute("title", "Places");
//        PlaceView placeView = placeService.getPlaceViewByPlaceId(new Integer(placeID));
        Place place = placeService.findById(new Integer(placeID));
        model.addAttribute("place", place);
        return "place_info";
    }


    @RequestMapping(value = "/delete", params="id", method = RequestMethod.GET)
    public String deletePlace(Integer id,
                              Model model){

        return "redirect:/places";
    }

    //Add equipment to place
//    PlaceEquipment addNewEquipment = new PlaceEquipment(placeService.findById(new Integer(placeIDorUserNickname)), equipmentService.findById(5), 2);
//    placeService.save(addNewEquipment);
}
