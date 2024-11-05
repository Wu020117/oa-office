package com.atguigu.auth.controller;


import com.atguigu.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {


    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public Result login(){
        Map<String,Object> mapLogin = new HashMap<>();
        mapLogin.put("token","admin-token");
        return Result.ok(mapLogin);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("info")
    public Result info(){
        Map<String,Object> mapInfo = new HashMap<>();
        mapInfo.put("roles","[admin]");
        mapInfo.put("name","admin");
        mapInfo.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(mapInfo);
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }

}