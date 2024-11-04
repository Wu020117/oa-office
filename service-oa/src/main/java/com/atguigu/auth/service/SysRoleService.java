package com.atguigu.auth.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户id获取角色数据
     * @param id
     * @return
     */
    Map<String,Object> findRoleByAdminId(Long id);


    /**
     * 分配角色
     * @param assginRoleVo
     */
    void doAssign(AssginRoleVo assginRoleVo);
}