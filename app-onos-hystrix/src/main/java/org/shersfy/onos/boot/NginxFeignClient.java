package org.shersfy.onos.boot;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("app-nginx-sidecar-proxy")
public interface NginxFeignClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @ResponseBody
    String callHello();
}
