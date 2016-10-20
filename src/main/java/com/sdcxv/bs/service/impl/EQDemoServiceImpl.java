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
    private boolean setFlag = false;
    private boolean resetFlag = false;
    private int clientConuter = 0;//等待的线程数量
    private boolean killFlag = false;
    private boolean sendFlag = false;
//    boolean newEQ = false;

    public EQ setEQStatus(EQ eqJSONString) {
        serverEQ = eqJSONString;
//        newEQ = true;
        return serverEQ;
    }

    public EQ setEQChange(EQ eqJSONString) {
        eqChange = eqJSONString;
        return eqChange;
    }

    /*
    * 当EQ变化量的X,Y均为0时，即无变化时，挂起0.2秒。否则返回EQ+EQ变化量
    * */
    public EQ getClientEQ() {
        clientConuter += 1;//等待的线程数量加1
        while (0 == eqChange.getX() && 0 == eqChange.getY() && setFlag) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (setFlag || resetFlag) {
                setFlag = false;
                resetFlag = false;
                clientConuter -= 1;//等待的线程数量减1
                return serverEQ;
            }
            //only for test
            if (killFlag && clientConuter > 0) {
                clientConuter -= 1;
                if (0 == clientConuter) {
                    killFlag = false;
                }
                return new EQ();
            }
        }
        serverEQ.setVolume(serverEQ.getVolume() + eqChange.getVolume());
        serverEQ.setPower(serverEQ.getPower() + eqChange.getPower());
        serverEQ.setX(serverEQ.getX() + eqChange.getX());
        serverEQ.setY(serverEQ.getY() + eqChange.getY());
        serverEQ.setZ(serverEQ.getZ() + eqChange.getZ());
        eqChange = new EQ();
        clientConuter -= 1;//等待的线程数量减1
        return checkEQ(serverEQ);
    }

    public EQ getASKEQ() {
//        while (!newEQ) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        newEQ = false;
        return serverEQ;
    }

    /**
     * 逐项设置EQ，若不设置该项需设为无效值
     */
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
        if (11 != eqJSONString.getZ()) {
            serverEQ.setZ(eqJSONString.getZ());
        }
        setFlag = true;
        return serverEQ;
    }

    /**
     * 重置EQ和EQ变化量
     */
    public void reset() {
        resetFlag = true;
        eqChange = new EQ();
        serverEQ.setX(0);
        serverEQ.setY(0);
    }

    private EQ checkEQ(EQ eq) {
        if (eq.getVolume() < 0) {
            eq.setVolume(0);
        }
        if (eq.getVolume() > 32) {
            eq.setVolume(32);
        }
        if (eq.getX() < -10) {
            eq.setX(-10);
        }
        if (eq.getX() > 10) {
            eq.setX(10);
        }
        if (eq.getY() < -10) {
            eq.setY(-10);
        }
        if (eq.getY() > 10) {
            eq.setY(10);
        }
        if (eq.getZ() < 0) {
            eq.setZ(0);
        }
        if (eq.getZ() > 10) {
            eq.setZ(10);
        }
        return eq;
    }

    /*only for test*/
    //kill all
    public void diediedie() {
        resetFlag = true;
    }

    public int getClientNumber() {
        return clientConuter;
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
