package com.myapp.rest.version1;

import com.myapp.entity.Place;
import com.myapp.service.PlaceService;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/v1/place")
public class RestPlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/all",
            method = RequestMethod.GET,
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody List<Place> getAllPlace(){
        return placeService.findAll();
    }

    @RequestMapping(value="/{placeId}",
                    method = RequestMethod.GET,
                    headers = {"Accept=text/xml, application/json"})
    public @ResponseBody Place getPlace(@PathVariable Integer placeId){
        return placeService.findById(placeId);
    }

    @RequestMapping(value="/{placeId}",
            method = RequestMethod.PUT,
            headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePlace(@PathVariable Integer placeId,
                            @RequestBody Place place){
        place.set_id(placeId);
        Date currentDate = new Date();
        place.setUpdated(currentDate);
        placeService.save(place);
    }

//    @RequestMapping(value="/add", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void addPlace(@RequestParam String name,
//                         @RequestParam String longitude,
//                         @RequestParam String latitude,
//                         @RequestParam String description,
//                         @RequestParam Integer creator){
//        User user = userService.findById(creator);
//        Place place = new Place(name, longitude, latitude, description, creator);
//        placeService.save(place);
//    }


    @RequestMapping(value="/add",
                    method = RequestMethod.POST,
                    headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Place createPlace(@RequestBody Place place){
        //TODO: must to check user another
//        User currentUser = userService.getLoggedUser();
//        ((Place)result.getTarget()).setCreator(currentUser);

//        if(userService.getLoggedUser() == null){
//            throw new BindException("Please authorize");
//        }
//        place.setCreator(currentUser);

        // Create current date and set created and updated
        Date currentDate = new Date();
        place.setCreated(currentDate);
        place.setUpdated(currentDate);

        placeService.save(place);
//        response.setHeader("Location", "/places/" + place.getId());
        return place;
    }

    @RequestMapping(value="/{placeId}",
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlace(@PathVariable Integer placeId){
        Place place = placeService.findById(placeId);
        if(place != null){
            placeService.delete(place);
        }
    }

}
