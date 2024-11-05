package com.atguigu.auth.service.impl;

import com.atguigu.auth.mapper.SysMenuMapper;
import com.atguigu.auth.service.SysMenuService;
import com.atguigu.model.system.SysMenu;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.auth.utils.MenuHelper;

import java.io.Serializable;
import java.util.List;


@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {



    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
//        全部权限列表
//        1、查询所有菜单数据
        List<SysMenu> sysMenuList = baseMapper.selectList(null);


//        2、构建树形结构
        List<SysMenu>  resultMenu = MenuHelper.buildTree(sysMenuList);
        return resultMenu;
    }


    @Override
    public boolean removeById(Serializable id){
        int count = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId,id));
        if(count>0){
            return false;
        }else{
            return super.removeById(id);
        }
    }
}
