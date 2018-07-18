package org.shersfy.onos.service.impl;

import org.shersfy.onos.service.OnosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OnosServiceImpl implements OnosService{

    private static final Logger LOGGER = LoggerFactory.getLogger(OnosService.class);
    
    @Override
    public void receivedData(String data) {
        LOGGER.info("received msg: {}", data);
    }

}
