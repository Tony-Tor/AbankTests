package com.testforwork.AbankTests.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "gifs", url = "${application.url.gifs}")
public interface GIFBrokeRich {
    @RequestMapping(method = RequestMethod.GET, value = "{imageID}.gif", produces = "image/gif")
    byte[] getGif(@PathVariable String imageID);
}
