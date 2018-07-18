package org.shersfy.accel.controller;

import javax.annotation.Resource;

import org.shersfy.accel.service.AccelService;
import org.shersfy.common.beans.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dial")
public class AccelController {
    
    @Resource
    private AccelService accelService;
    
    @RequestMapping("/alloc")
    public Result allocIP() {
        Result res = new Result();
        res.setModel(accelService.findIp());
        return res;
    }

}
