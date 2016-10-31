package com.sdcxv.bs.controller;

import com.sdcxv.bs.model.EQ;
import com.sdcxv.bs.service.EQDemoService;
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
    private static Logger log = LoggerFactory.getLogger(EQDemoController.class);
    private EQDemoService eqDemoService;

    @Autowired
    public void setEQDemoService(EQDemoService eqDemoService) {
        this.eqDemoService = eqDemoService;
    }

    /*
    * 客户端发送当前状态到服务器
    * */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setEQStatus(@RequestBody EQ eqJSONString) {
        return eqDemoService.setEQStatus(eqJSONString);
    }

    /*
    * ASK端发送数据变化量到服务器
    * */
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setEQChange(@RequestBody EQ eqJSONString) {
        return eqDemoService.setEQChange(eqJSONString);
    }

    /*
    * 设置服务器端EQ数值
    * */
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setEQ(@RequestBody EQ eqJSONString) {
        return eqDemoService.setEQ(eqJSONString);
    }

    /*
    * 客户端获取EQ数值
    * */
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getClientEQ() {
        return eqDemoService.getClientEQ();
    }

    /*
    * ASK端获取EQ数值
    * */
    @RequestMapping(value = "/ask", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getASKEQ() {
        return eqDemoService.getASKEQ();
    }


    /*
    * 重置服务端EQ数值
    * */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ reset() {
        return eqDemoService.reset();
    }


    @RequestMapping(value = "/kill", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void diediedie() {
        eqDemoService.diediedie();
    }

    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    int getClientNumber() {
        return eqDemoService.getClientNumber();
    }
//    @RequestMapping(value = "/status", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public
//    @ResponseBody
//    EQ clientGet() {
//        return eqDemoService.getFinalEQ();
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public void setEQ(@RequestParam("eq") String eqRequest) {
//        EQ eq = null;
//        try {
//            eq = new ObjectMapper().readValue(eqRequest, EQ.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        log.info("Got from /eq/set, eq=" + eqRequest);
//        eqDemoService.setEQ(eq);
//    }
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    public
//    @ResponseBody
//    EQ getEQInJson() {
//        log.info("Getting EQ Setting.");
//        EQ temp = eqDemoService.getEQ();
//        eqDemoService.setEQ(new EQ());
//        if (0 == temp.getY() && 0 == temp.getX())
//            return null;
//        else
//            return temp;
//    }
//
//    @RequestMapping("/reset")
//    @ResponseStatus(HttpStatus.OK)
//    public void reset() {
//        eqDemoService.setEQ(new EQ());
//    }
//
//    @RequestMapping("/killthread")
//    @ResponseStatus(HttpStatus.OK)
//    public void killThread() {
//        eqDemoService.endThread();
//    }
//
//    @RequestMapping(value = "/delay", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    EQ getEQInJsonDelay() {
//        log.info("Getting EQ Setting.");
//        while (0 == eqDemoService.getEQ().getX() && 0 == eqDemoService.getEQ().getY()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (eqDemoService.endThread()) {
//                return new EQ();
//            }
//        }
//        EQ temp = eqDemoService.getEQ();
//        eqDemoService.setEQ(new EQ());
//        return temp;
//    }
//
//    @RequestMapping("/tone/set/{type}")
//    @ResponseStatus(HttpStatus.OK)
//    public void setTone(@PathVariable("type") String tone) {
//        eqDemoService.setTone(tone);
//    }
//
//    @RequestMapping(value = "/tone/get", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    String getTone() {
//        return eqDemoService.getTone();
//    }
}
