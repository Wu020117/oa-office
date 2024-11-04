package com.atguigu.auth.service.impl;


import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.auth.mapper.SysUserRoleMapper;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.auth.service.SysUserRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public Map<String, Object> findRoleByAdminId(Long userId) {
//        1、查询所有角色
        List<SysRole> allRolesList = baseMapper.selectList(null);

//        拥有的角色id
//        2、根据userid查询 角色用户关系表 查询userid对应所有角色的id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);

//        从查询出来的用户id对应角色list集合 获取所有角色id
//        List<Long> roleList = new ArrayList<>();
//        for (SysUserRole sysUserRole : existUserRoleList){
//            Long roleId = sysUserRole.getRoleId();
//            roleList.add(roleId);
//        }
        List<Long> existRoleList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());
//        3、 根据查询所有角色id 找到对应角色信息
//        根据角色id到所有的角色list集合中进行比较
        List<SysRole> assignRoleList = new ArrayList<>();
        for (SysRole sysRole : allRolesList){
//            比较
            if (existRoleList.contains(sysRole.getId())){
                assignRoleList.add(sysRole);
            }
        }

//        4、 把得到的两部分数据封装map集合 返回
        Map<String,Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList",assignRoleList);
        roleMap.put("allRolesList",allRolesList);
        return roleMap;
    }




//    2、分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(
                SysUserRole::getUserId,assginRoleVo.getUserId()));

        for (Long roleId : assginRoleVo.getRoleIdList()){
            if (StringUtils.isEmpty(roleId)) {
                continue;
            }
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }
    }




}
