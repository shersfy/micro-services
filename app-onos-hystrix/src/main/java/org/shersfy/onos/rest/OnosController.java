package org.shersfy.onos.rest;

import javax.annotation.Resource;

import org.shersfy.onos.boot.AccelFeignClient;
import org.shersfy.onos.boot.NginxFeignClient;
import org.shersfy.onos.service.OnosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/onos")
public class OnosController {
    
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Resource
    private OnosService onosService;
    @Autowired
    private EurekaClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccelFeignClient accelFeignClient;
    @Autowired
    private NginxFeignClient nginxFeignClient;
    
    @GetMapping("/version")
    public String getVersion() {
        return onosService.getVersion();
    }
    
    @GetMapping("/next/accel-ppp")
    public String getNextServerInstance() {
        
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("app-accel-ppp", false);
        return instance.getHomePageUrl();
    }
    
    @GetMapping("/user/offline")
    @HystrixCommand(fallbackMethod="offlineFallback") // ribbon断路器
    public boolean callAccelUserOffline(String username) {
        return restTemplate.getForObject("http://app-accel-ppp/offline/"+username, Boolean.class);
    }
    
    @GetMapping("/user/offline2")
    public boolean callAccelUserOffline2(String username) {
        return accelFeignClient.callPPPUserOffline(username);
    }
    
    public boolean offlineFallback(String username) {
        LOGGER.warn("callAccelUserOffline() failed, username={}", username);
        return false;
    }
    
    
    @GetMapping("/nginx/hello")
    public String callHello() {
        return nginxFeignClient.callHello();
    }

    
}
