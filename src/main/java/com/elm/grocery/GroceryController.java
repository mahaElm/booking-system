
package com.elm.grocery;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.LogManager.*;
@RestController
public class GroceryController{
    private final static Logger logger = getLogger(GroceryController.class);

    @RequestMapping(value= "/load", method = RequestMethod.GET)
    public String getMethod(String id) {

        return id ;
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String  submit (@RequestBody FormClass form) { //make obj

        return {String Name= form.getName();
        String mail = form.getMail();
        String phone = form.getPhonee();
        String bdate = form.getBdate();
        String btime = form.getBtime();}
, ;
    }

}


