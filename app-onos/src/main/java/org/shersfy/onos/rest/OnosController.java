package org.shersfy.onos.rest;

import javax.annotation.Resource;

import org.shersfy.onos.boot.OnosFeignClient;
import org.shersfy.onos.service.OnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/onos")
public class OnosController {
    
    @Resource
    private OnosService onosService;
    @Autowired
    private EurekaClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OnosFeignClient onosFeignClient;
    
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
    public boolean callPPPUserOffline(String username) {
        return restTemplate.getForObject("http://app-accel-ppp/offline/"+username, Boolean.class);
    }
    
    @GetMapping("/user/offline2")
    public boolean callPPPUserOffline2(String username) {
        return onosFeignClient.callPPPUserOffline(username);
    }

}
