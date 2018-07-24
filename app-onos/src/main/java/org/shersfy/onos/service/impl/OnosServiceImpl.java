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
    
    @Value("${onos.version}")
    private String version;
    
    @Value("${onos.subversion}")
    private String subVersion;
    
    @Override
    public void receivedData(String data) {
        LOGGER.info("received msg: {}", data);
    }

    @Override
    public String getVersion() {
        return String.format("ONOS version: %s.%s", version, subVersion);
    }

}
