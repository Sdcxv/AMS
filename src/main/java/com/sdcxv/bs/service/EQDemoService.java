package com.sdcxv.bs.service;

import com.sdcxv.bs.model.EQ;

/**
 * EQ Demo 服务器接口
 * Created by Xudong.Liu on 2016/10/10.
 */
public interface EQDemoService {
    /**
     * 设置Demo EQ
     */
    EQ setEQ(EQ eqJSONString);

    /**
     * 设置EQ状态
     */

    EQ setEQStatus(EQ eqJSONString);

    /**
     * 设置EQ变化量
     */
    EQ setEQChange(EQ eqJSONString);

    /**
     * 客户端获取EQ计算结果
     */
    EQ getClientEQ();

    /**
     * ASK获取EQ当前状态
     */
    EQ getASKEQ();

    /**
     * 重置服务端EQ数据
     */
    EQ reset();

    void diediedie();

    int getClientNumber();

    EQ getLastEQ();

    EQ getFavoriteEQ();

    EQ setFavoriteEQ();

    EQ getRandomEQ();

}
