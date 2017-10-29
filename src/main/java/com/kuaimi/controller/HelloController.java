package com.kuaimi.controller;

import com.kuaimi.message.response.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : chenwei
 * @create : 2017-10-04 15:47
 */
@Api(value = "API - HelloController", description = "Hello demo相关API")
@Controller
public class HelloController {

    @ApiOperation(value = "Hello World", notes = "显示hello world")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "名字", required = true, paramType = "path")
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "请求成功", response = BaseResponse.class)
    })
    @GetMapping(path = "/hello/{name}")
    @ResponseBody
    public BaseResponse hello(@PathVariable("name") String name) {
        return new BaseResponse("hello" + name);
    }

    @GetMapping(path = "/swagger")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }

    /*@RequestMapping("/hello")
    public String hello() {
        return "hello";
    }*/
}
