package org.shersfy.jetty.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JettyController {
    
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/")
    public Object index() {
        return "Welcom Jetty Application";
    }
}
