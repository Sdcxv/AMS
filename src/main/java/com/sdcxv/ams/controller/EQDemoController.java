package com.sdcxv.ams.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sdcxv.ams.model.EQ;
import com.sdcxv.ams.service.EQDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(value = HttpStatus.OK)
    public void setEQ(@RequestParam("eq") String eq) {
        log.info("Got from /eq/set, eq=", eq);
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    EQ getEQInJson() {
        log.info("Getting EQ Setting.");
        return eqDemoService.getEQ();
    }
}
