package org.shersfy.onos.rest;

import javax.annotation.Resource;

import org.shersfy.onos.service.OnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/onos")
public class OnosController {
    
    @Resource
    private OnosService onosService;
    
    @Autowired
    private EurekaClient discoveryClient;
    
    @GetMapping("/version")
    public String getVersion() {
        return onosService.getVersion();
    }
    
    @GetMapping("/next/accel-ppp")
    public String getNextServerInstance() {
        
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("app-accel-ppp", false);
        return instance.getHomePageUrl();
    }

}
