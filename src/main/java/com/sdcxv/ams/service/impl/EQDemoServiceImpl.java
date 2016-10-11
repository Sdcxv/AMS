package com.sdcxv.ams.service.impl;

import com.sdcxv.ams.model.EQ;
import com.sdcxv.ams.service.EQDemoService;
import org.springframework.stereotype.Service;

/**
 * Created by Xudong.Liu on 2016/10/10.
 */
@Service
public class EQDemoServiceImpl implements EQDemoService {
    public void setEQ(EQ eq) {
        System.out.println("setting eq");
    }

    public EQ getEQ() {
        EQ eq = new EQ();
        eq.setX(1);
        return eq;
    }
}
