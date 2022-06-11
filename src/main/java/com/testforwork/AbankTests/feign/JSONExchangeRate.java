package com.testforwork.AbankTests.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "exchangeRate", url = "${application.url.exchangeRate}")
public interface JSONExchangeRate {

    @RequestMapping(method = RequestMethod.GET, value = "latest.json?" +
            "app_id=${application.app_id.openexchange}", produces = "application/json")
    String getToday();

    @RequestMapping(method = RequestMethod.GET, value = "historical/{data}.json?" +
            "app_id=${application.app_id.openexchange}", produces = "application/json")
    String getYesterday(@PathVariable String data);
}
