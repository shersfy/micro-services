package org.shersfy.jetty.rest;

import java.util.Date;

import org.shersfy.jetty.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JettyController {
    
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index")
    public Object index() {
        return "Welcom Jetty Application";
    }
    
    @PostMapping("/user")
    public Object getUser(String name) {
    	User user = new User();
    	user.setId(System.nanoTime());
    	user.setName(name);
    	user.setBirthday(new Date());
    	return user;
    }
}
