package org.shersfy.accel.controller;

import javax.annotation.Resource;

import org.shersfy.accel.beans.Result;
import org.shersfy.accel.service.AccelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccelController {
    
    @Resource
    private AccelService accelService;
    
    @GetMapping("/dial/alloc")
    public Result allocIP() {
        Result res = new Result();
        res.setModel(accelService.findIp());
        return res;
    }
    
    @GetMapping("/offline/{username}")
    public boolean offline(@PathVariable("username")String username) {
        Result res = new Result();
        System.out.println(String.format("===========offline: %s================", username));
        res.setModel(username);
        return true;
    }

}
