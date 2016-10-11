package com.sdcxv.ams.service;

import com.sdcxv.ams.model.EQ;

/**
 * Created by Xudong.Liu on 2016/10/10.
 */
public interface EQDemoService {
    /**
     * 设置Demo EQ
    * */
    void setEQ(EQ eq);

    /**
     * 获取设置好的Demo EQ
     * */
    EQ getEQ();
}
