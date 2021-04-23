package com.controller;

import com.common.CommonResponse;
import com.common.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LMM
 * @date 14:34 2021/4/23
 * @desc
 */
@RestController
@Api("接口测试")
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ok")
    public CommonResponse test(){

        return new CommonResponse(StatusCode.SUCCESS,"测试ok");
    }
}
