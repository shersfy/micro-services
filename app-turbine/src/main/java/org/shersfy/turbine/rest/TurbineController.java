package org.shersfy.turbine.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TurbineController {
    
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
}
