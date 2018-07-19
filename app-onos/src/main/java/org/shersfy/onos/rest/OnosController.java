package org.shersfy.onos.rest;

import javax.annotation.Resource;

import org.shersfy.onos.service.OnosService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/onos")
public class OnosController {
    
    @Resource
    private OnosService onosService;
    
    @RequestMapping("/config")
    @ResponseBody
    public String getConfig() {
        return onosService.getConfig();
    }

}
