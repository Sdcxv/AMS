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
    private EQ serverEQ = new EQ();
    private EQ eqChange = new EQ();

    public EQ setEQ(EQ eqJSONString) {
        if (-1 != eqJSONString.getVolume()) {
            serverEQ.setVolume(eqJSONString.getVolume());
        }
        if (-1 != eqJSONString.getPower()) {
            serverEQ.setPower(eqJSONString.getPower());
        }
        if (11 != eqJSONString.getX()) {
            serverEQ.setX(eqJSONString.getX());
        }
        if (11 != eqJSONString.getY()) {
            serverEQ.setY(eqJSONString.getY());
        }
        if (-1 != eqJSONString.getZ()) {
            serverEQ.setZ(eqJSONString.getZ());
        }
        return serverEQ;
    }

    public EQ setEQStatus(EQ eqJSONString) {
        serverEQ = eqJSONString;
        return serverEQ;
    }

    public EQ setEQChange(EQ eqJSONString) {
        eqChange = eqJSONString;
        return eqChange;
    }

    public EQ getClientEQ() {
        serverEQ.setVolume(serverEQ.getVolume() + eqChange.getVolume());
        serverEQ.setPower(serverEQ.getPower() + eqChange.getPower());
        serverEQ.setX(serverEQ.getX()+serverEQ.getX());
        serverEQ.setY(serverEQ.getX()+serverEQ.getX());
        serverEQ.setZ(serverEQ.getZ()+serverEQ.getZ());
        return serverEQ;
    }

    public EQ getASKEQ() {
        return serverEQ;
    }

    public void reset() {
        serverEQ = new EQ();
    }


//    private EQ demoEQ = new EQ();
//    private boolean endThreadFlag = false;
//
//    public EQ setEQ(EQ eq) {
//        log.info("Setting demoEQ");
//        if (null != eq) {
//            demoEQ = eq;
//            log.info("Set demoEQ");
//        } else {
//            log.info("Got Null EQ");
//        }
//        return eq;
//    }
//
//    public EQ getEQ() {
//        return demoEQ;
//    }
//
//    public void setEndThreadFlag() {
//        endThreadFlag = true;
//    }
//
//    public boolean endThread() {
//        boolean temp = endThreadFlag;
//        endThreadFlag = false;
//        return temp;
//    }
}
