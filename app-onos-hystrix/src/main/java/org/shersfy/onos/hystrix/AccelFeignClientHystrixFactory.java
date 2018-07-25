package org.shersfy.onos.hystrix;


import org.shersfy.onos.boot.AccelFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class AccelFeignClientHystrixFactory implements FallbackFactory<AccelFeignClient> {

    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Override
    public AccelFeignClient create(Throwable cause) {
        LOGGER.warn("", cause);
        return new AccelFeignClient() {
            
            @Override
            public boolean callPPPUserOffline(String username) {
                LOGGER.warn("callAccelUserOffline2() failed, username={}", username);
                return false;
            }
        };
    }

}
