package com.testforwork.AbankTests.controllers;

import com.testforwork.AbankTests.exception.ServiceNotFoundException;
import com.testforwork.AbankTests.feign.GIFBrokeRich;
import com.testforwork.AbankTests.feign.JSONExchangeRate;
import com.testforwork.AbankTests.feign.JSONGifBrokeRich;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.LocalDate;

@RequestMapping("/benefits")
@RestController
public class BenefitsController {

    @Autowired
    private Logger logger;

    @Autowired
    private JSONExchangeRate rate;

    @Autowired
    private JSONGifBrokeRich brokeRich;

    @Autowired
    private GIFBrokeRich gifs;

    @GetMapping(value = "/{currency}", produces = "image/gif")
    public byte[] getBenefitsGif(@PathVariable String currency){
        String todayRatesJSON = rate.getToday();
        String yesterday = LocalDate.now().minusDays(1).toString();
        String yesterdayRatesJSON = rate.getYesterday(yesterday);

        JSONObject todayObject = new JSONObject(todayRatesJSON).getJSONObject("rates");
        JSONObject yesterdayObject = new JSONObject(yesterdayRatesJSON).getJSONObject("rates");
        BigDecimal todayRate;
        BigDecimal yesterdayRate;
        try {
            todayRate = todayObject.getBigDecimal(currency);
            yesterdayRate = yesterdayObject.getBigDecimal(currency);
        } catch (JSONException e){
            throw new ServiceNotFoundException("Unknown currency");
        }

        int compare = yesterdayRate.compareTo(todayRate);
        logger.info("getBenefitsGif" + todayRate + "-" + yesterdayRate);
        String objectString;
        if(compare > 0){
            objectString = brokeRich.getRich();
        } else {
            objectString = brokeRich.getBroke();
        }
        return getImage(objectString);
    }

    private byte[] getImage(String JSON){
        String object = new JSONObject(JSON).getJSONObject("data").getString("id");
        return gifs.getGif(object);
    }

}
