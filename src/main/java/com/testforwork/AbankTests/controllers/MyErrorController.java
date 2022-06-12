package com.testforwork.AbankTests.controllers;

import com.testforwork.AbankTests.exception.ServiceNotFoundException;
import com.testforwork.AbankTests.feign.GIFBrokeRich;
import com.testforwork.AbankTests.feign.JSONGifBrokeRich;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequestMapping()
@RestController
public class MyErrorController implements ErrorController {

    @Autowired
    private JSONGifBrokeRich brokeRich;

    @Autowired
    private GIFBrokeRich gifs;

    @GetMapping(value = "/error", produces = "image/gif")
    public byte[] getBenefitsGif(){

        String objectString = brokeRich.getNotFound();

        return getImage(objectString);
    }

    private byte[] getImage(String JSON){
        String object = new JSONObject(JSON).getJSONObject("data").getString("id");
        return gifs.getGif(object);
    }

}
