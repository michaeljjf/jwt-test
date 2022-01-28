package com.jiangjf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangjf
 * @date 2022/1/28
 */
@RestController
public class IndexController {
    @RequestMapping
    public String hello() {
        return "hello";
    }
}
