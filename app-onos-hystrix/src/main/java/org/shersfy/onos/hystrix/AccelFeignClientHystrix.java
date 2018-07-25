package org.shersfy.onos.hystrix;


import org.shersfy.onos.boot.AccelFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccelFeignClientHystrix implements AccelFeignClient {
    
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Override
    public boolean callPPPUserOffline(String username) {
        LOGGER.warn("callAccelUserOffline2() failed, username={}", username);
        return false;
    }

}
