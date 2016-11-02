package com.sdcxv.bs.service.impl;

import com.sdcxv.bs.model.EQ;
import com.sdcxv.bs.service.EQDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Stack;

/**
 * Created by Xudong.Liu on 2016/10/10.
 */
@Service
public class EQDemoServiceImpl implements EQDemoService {
    private static Logger log = LoggerFactory.getLogger(EQDemoServiceImpl.class);
    private EQ serverEQ = new EQ();
    private EQ favoriteEQ = new EQ();
    private int clientCounter = 0;//等待的线程数量
    private boolean killFlag = false;
    private boolean changeFlag = false;
    //上次使用的EQ数据的stack，最大存储5个
    Stack<EQ> lastEQStack = new Stack<>();

    //初始化函数
    @PostMapping
    public void init() {
        log.info("EQ Service Initialized");
    }

    public EQ setEQStatus(EQ eqJSONString) {
        serverEQ = eqJSONString;
        return checkEQ(serverEQ);
    }

    public EQ setEQChange(EQ eqJSONString) {
        //EQ历史结果入栈
        if (lastEQStack.size() > 5) {
            lastEQStack.pop();
        }
        lastEQStack.push((EQ) serverEQ.clone());
        changeFlag = true;//EQ变化标记
        EQ bufferEQ = (EQ) serverEQ.clone();
        //计算EQ数据变化结果
        serverEQ.setConnection(serverEQ.getConnection() + eqJSONString.getConnection());
        serverEQ.setSleep(serverEQ.getSleep() + eqJSONString.getSleep());
        serverEQ.setVolume(serverEQ.getVolume() + eqJSONString.getVolume());
        serverEQ.setController(serverEQ.getController() + eqJSONString.getController());
        serverEQ.setX(serverEQ.getX() + eqJSONString.getX());
        serverEQ.setY(serverEQ.getY() + eqJSONString.getY());
        serverEQ.setZ(serverEQ.getZ() + eqJSONString.getZ());
        //返回上个EQ数据
        return checkEQ(bufferEQ);
    }

    /**
     * 当EQ变化量的X,Y均为0时，即无变化时，挂起0.2秒。否则返回EQ+EQ变化量
     */
    public EQ getClientEQ() {
        clientCounter += 1;//等待的线程数量加1
        while (!changeFlag && !killFlag/* && 0 == eqChange.getX() && 0 == eqChange.getY()*/) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (1 == clientCounter) {
            changeFlag = false;
            killFlag = false;
            if (1 < serverEQ.getController()) {
                EQ bufferEQ = (EQ) serverEQ.clone();
                //重置为播放状态 并 返回
                serverEQ.setController(1);
                clientCounter -= 1;//等待的线程数量减1
                return checkEQ(bufferEQ);
            }
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
        //EQ历史结果入栈
        if (lastEQStack.size() > 5) {
            lastEQStack.pop();
        }
        lastEQStack.push((EQ) serverEQ.clone());
        if (-1 != eqJSONString.getConnection()) {
            serverEQ.setConnection(eqJSONString.getConnection());
        }
        if (-1 != eqJSONString.getSleep()) {
            serverEQ.setSleep(eqJSONString.getSleep());
        }
        if (-1 != eqJSONString.getVolume()) {
            serverEQ.setVolume(eqJSONString.getVolume());
        }
        if (-1 != eqJSONString.getController()) {
            serverEQ.setController(eqJSONString.getController());
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
        changeFlag = true;
        return checkEQ(serverEQ);
    }

    /**
     * 重置EQ
     */
    public EQ reset() {
        EQ bufferEQ = (EQ) serverEQ.clone();
        changeFlag = true;
        serverEQ.setController(0);
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
        if (eq.getController() < -1) {
            eq.setController(-1);
        }
        if (eq.getController() > 3) {
            eq.setController(3);
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

    @Override
    public EQ getLastEQ() {
        changeFlag = true;//EQ变化标记
        if (lastEQStack.empty()) {
            return new EQ();
        } else {
            serverEQ = lastEQStack.pop();
            return checkEQ(serverEQ);
        }
    }

    @Override
    public EQ getFavoriteEQ() {
        changeFlag = true;//EQ变化标记
        return checkEQ(favoriteEQ);
    }

    @Override
    public EQ setFavoriteEQ() {
        favoriteEQ = (EQ) serverEQ.clone();
        return checkEQ(favoriteEQ);
    }

    @Override
    public EQ getRandomEQ() {
        EQ bufferEQ = (EQ) serverEQ.clone();
        //EQ历史结果入栈
        if (lastEQStack.size() > 5) {
            lastEQStack.pop();
        }
        lastEQStack.push((EQ) serverEQ.clone());
        changeFlag = true;//EQ变化标记
        SecureRandom secureRandom = new SecureRandom();
        serverEQ.setX(secureRandom.nextInt(21) - 10);
        serverEQ.setY(secureRandom.nextInt(21) - 10);
        return checkEQ(bufferEQ);
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
