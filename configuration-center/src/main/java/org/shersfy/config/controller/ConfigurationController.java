package org.shersfy.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigurationController {
    
    @Value("${spring.cloud.config.server.git.uri}")
    private String gitUri;
    
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("gitUri", gitUri);
        return mav;
    }

}
