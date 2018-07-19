package org.shersfy.onos.service.impl;

import org.shersfy.onos.service.OnosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class OnosServiceImpl implements OnosService{

    private static final Logger LOGGER = LoggerFactory.getLogger(OnosService.class);
    
    @Value("${logging.file}")
    private String loggingFile;
    
    @Value("${logging.level}")
    private String loggingLevel;
    
    @Override
    public void receivedData(String data) {
        LOGGER.info("received msg: {}", data);
    }

    @Override
    public String getConfig() {
        return String.format("level: %s, file:%s", loggingLevel, loggingFile);
    }

}
