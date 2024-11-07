package com.atguigu.auth.controller;


import com.atguigu.auth.service.SysMenuService;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.system.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {


    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation(value = "查询所有菜单和角色分配的菜单那")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId){
        List<SysMenu> sysMenuList = sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(sysMenuList);
    }

    @ApiOperation(value = "角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo){
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();

    }
//    菜单列表接口
//    构建树形结构
    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysMenu>  list = sysMenuService.findNodes();
        return Result.ok(list);
    }

//    新增
    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

//    修改
    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result update(@RequestBody SysMenu sysMenu){
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }


//    删除
    @ApiOperation(value = "根据id删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        sysMenuService.removeById(id);
        return Result.ok();
    }



}
