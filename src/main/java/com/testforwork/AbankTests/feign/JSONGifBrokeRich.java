package com.testforwork.AbankTests.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "brokeRich", url = "${application.url.brokeRich}")
public interface JSONGifBrokeRich {
    @RequestMapping(method = RequestMethod.GET, value = "?" +
            "api_key=${application.app_id.giphy}&tag=${application.setting.tag.rich}&rating=${application.setting.rating}", produces = "application/json")
    String getRich();

    @RequestMapping(method = RequestMethod.GET, value = "?" +
            "api_key=${application.app_id.giphy}&tag=${application.setting.tag.broke}&rating=${application.setting.rating}", produces = "application/json")
    String getBroke();
}
