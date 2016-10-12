package com.sdcxv.bs.service.impl;

import com.sdcxv.bs.model.EQ;
import com.sdcxv.bs.service.EQDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Xudong.Liu on 2016/10/10.
 */
@Service
public class EQDemoServiceImpl implements EQDemoService {
    private static Logger log = LoggerFactory.getLogger(EQDemoServiceImpl.class);
    private EQ demoEQ = new EQ();
    private boolean endThreadFlag = false;

    public void setEQ(EQ eq) {
        log.info("Setting demoEQ");
        if (null != eq) {
            demoEQ = eq;
            log.info("Set demoEQ");
        } else {
            log.info("Got Null EQ");
        }
    }

    public EQ getEQ() {
        return demoEQ;
    }

    public boolean endThread() {
        return false;
    }
}
