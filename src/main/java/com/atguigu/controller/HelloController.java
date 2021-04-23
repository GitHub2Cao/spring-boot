package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//public class HelloController {
//
//    @ResponseBody
//    @RequestMapping("/hello")
//    public String hello(){
//        return "Hello World!";
//    }
//}
@Controller
@RequestMapping("/app")
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @ResponseBody
    @GetMapping(value = "/{accountId}")
    public String getAccountById(@PathVariable("accountId") Long accountId) {
        return "account __" + accountId;
    }
}
