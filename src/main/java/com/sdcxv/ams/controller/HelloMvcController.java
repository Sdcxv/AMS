package com.sdcxv.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sdcxv on 2016/3/2.
 */
@Controller
@RequestMapping("/hello")
public class HelloMvcController {
    @RequestMapping("/mvc")
    public String helloMvc() {
        return "home";
    }
}
