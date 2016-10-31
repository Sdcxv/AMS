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
    private int clientCounter = 0;//等待的线程数量
    private boolean killFlag = false;
    private boolean changeFlag = false;

    public EQ setEQStatus(EQ eqJSONString) {
        serverEQ = eqJSONString;
        return checkEQ(serverEQ);
    }

    public EQ setEQChange(EQ eqJSONString) {
        changeFlag = true;
        eqChange = eqJSONString;
        EQ bufferEQ = (EQ) serverEQ.clone();

        serverEQ.setConnection(serverEQ.getConnection() + eqChange.getConnection());
        serverEQ.setVolume(serverEQ.getVolume() + eqChange.getVolume());
        serverEQ.setTonescape(serverEQ.getTonescape() + eqChange.getTonescape());
        serverEQ.setX(serverEQ.getX() + eqChange.getX());
        serverEQ.setY(serverEQ.getY() + eqChange.getY());
        serverEQ.setZ(serverEQ.getZ() + eqChange.getZ());

        return checkEQ(bufferEQ);
    }

    /*
    * 当EQ变化量的X,Y均为0时，即无变化时，挂起0.2秒。否则返回EQ+EQ变化量
    * */
    public EQ getClientEQ() {
        clientCounter += 1;//等待的线程数量加1
        while (!changeFlag && !killFlag/* && 0 == eqChange.getX() && 0 == eqChange.getY()*/) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (setFlag || resetFlag) {
                setFlag = false;
                resetFlag = false;
                clientCounter -= 1;//等待的线程数量减1
                return serverEQ;
            }
//            //only for test
//            if (killFlag && clientCounter > 0) {
//                clientCounter -= 1;
//                if (0 == clientCounter) {
//                    killFlag = false;
//                }
//                return new EQ();
//            }
        }
        if (1 == clientCounter) {
            changeFlag = false;
            killFlag = false;
            eqChange = new EQ();
        }
        if (killFlag) {
            clientCounter -= 1;//等待的线程数量减1
            return checkEQ(new EQ());
        }
        clientCounter -= 1;//等待的线程数量减1
        return checkEQ(serverEQ);
    }

    public EQ getASKEQ() {
        return checkEQ(serverEQ);
    }

    /**
     * 逐项设置EQ，若不设置该项需设为无效值
     */
    public EQ setEQ(EQ eqJSONString) {
        if (-1 != eqJSONString.getConnection()) {
            serverEQ.setConnection(eqJSONString.getConnection());
        }
        if (-1 != eqJSONString.getVolume()) {
            serverEQ.setVolume(eqJSONString.getVolume());
        }
        if (-1 != eqJSONString.getTonescape()) {
            serverEQ.setTonescape(eqJSONString.getTonescape());
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
        return checkEQ(serverEQ);
    }

    /**
     * 重置EQ和EQ变化量
     */
    public EQ reset() {
        EQ bufferEQ = (EQ) serverEQ.clone();
        resetFlag = true;
        eqChange = new EQ();
        serverEQ.setTonescape(0);
        serverEQ.setX(0);
        serverEQ.setY(0);
        return bufferEQ;
    }

    private EQ checkEQ(EQ eq) {
        if (eq.getConnection() < 0) {
            eq.setConnection(0);
        }
        if (eq.getConnection() > 1) {
            eq.setConnection(1);
        }
        if (eq.getTonescape() < 0) {
            eq.setTonescape(0);
        }
        if (eq.getTonescape() > 1) {
            eq.setTonescape(1);
        }
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
        killFlag = true;
    }

    public int getClientNumber() {
        return clientCounter;
    }
}
