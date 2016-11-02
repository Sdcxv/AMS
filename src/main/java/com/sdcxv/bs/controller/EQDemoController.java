package com.sdcxv.bs.controller;

import com.sdcxv.bs.model.EQ;
import com.sdcxv.bs.service.EQDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sdcxv on 2016/3/2.
 */
@Api(value = "/eq", description = "Operations about EQ")
@Controller
@RequestMapping("/eq")
public class EQDemoController {
    private static Logger log = LoggerFactory.getLogger(EQDemoController.class);
    private EQDemoService eqDemoService;

    @Autowired
    public void setEQDemoService(EQDemoService eqDemoService) {
        this.eqDemoService = eqDemoService;
    }

    /**
     * 客户端发送当前状态到服务器
     */
    @ApiOperation("提交客户端状态")
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setEQStatus(@RequestBody EQ eqJSONString) {
        return eqDemoService.setEQStatus(eqJSONString);
    }

    /**
     * ASK端发送数据变化量到服务器
     */
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setEQChange(@RequestBody EQ eqJSONString) {
        return eqDemoService.setEQChange(eqJSONString);
    }

    /**
     * 设置服务器端EQ数值
     */
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setEQ(@RequestBody EQ eqJSONString) {
        return eqDemoService.setEQ(eqJSONString);
    }

    /**
     * 客户端获取EQ数值
     */
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getClientEQ() {
        return eqDemoService.getClientEQ();
    }

    /**
     * ASK端获取EQ数值
     */
    @RequestMapping(value = "/ask", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getASKEQ() {
        return eqDemoService.getASKEQ();
    }


    /**
     * 重置服务端EQ数值
     */
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

    /**
     * 获取上一个选用的EQ
     */
    @RequestMapping(value = "/last", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getLastEQ() {
        return eqDemoService.getLastEQ();
    }

    /**
     * 设置最爱的EQ
     */
    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    EQ setFavoriteEQ() {
        return eqDemoService.setFavoriteEQ();
    }

    /**
     * 获取最爱的EQ
     */
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getFavoriteEQ() {
        return eqDemoService.getFavoriteEQ();
    }

    /**
     * 获取随机的EQ
     */
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    EQ getRandomEQ() {
        return eqDemoService.getRandomEQ();
    }
}
