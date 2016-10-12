package com.sdcxv.bs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdcxv.bs.model.EQ;
import com.sdcxv.bs.service.EQDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Sdcxv on 2016/3/2.
 */
@Controller
@RequestMapping("/eq")
public class EQDemoController {
    private static Logger log = LoggerFactory.getLogger(CourseController.class);
    private EQDemoService eqDemoService;

    @Autowired
    public void setEQDemoService(EQDemoService eqDemoService) {
        this.eqDemoService = eqDemoService;
    }

    @RequestMapping("/set")
    @ResponseStatus(HttpStatus.OK)
    public void setEQ(@RequestParam("eq") String eqRequest) {
        EQ eq = null;
        try {
            eq = new ObjectMapper().readValue(eqRequest, EQ.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Got from /eq/set, eq=" + eqRequest);
        eqDemoService.setEQ(eq);
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    EQ getEQInJson() {
        log.info("Getting EQ Setting.");
        return eqDemoService.getEQ();
    }

    @RequestMapping(value = "/delay", method = RequestMethod.GET)
    public
    @ResponseBody
    EQ getEQInJsonDelay() {

        log.info("Getting EQ Setting.");
        while (0 == eqDemoService.getEQ().getX() && 0 == eqDemoService.getEQ().getY()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(eqDemoService.endThread()){
                return new EQ();
            }
        }
        EQ temp = eqDemoService.getEQ();
        eqDemoService.setEQ(new EQ());
        return temp;
    }
}
