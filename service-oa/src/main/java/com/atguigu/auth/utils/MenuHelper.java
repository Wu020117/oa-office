package com.atguigu.auth.utils;


import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 *     构建菜单数据
 * </P>
 */
public class MenuHelper {


    /**
     * 使用递归方法构建菜单数据
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        List<SysMenu> trees = new ArrayList<>();
//        把所有菜单数据进行遍历
        for (SysMenu sysMenu : sysMenuList){
//            递归入口进入
//            parentId = 0 是入口
            if (sysMenu.getParentId().longValue() == 0){
//                开始递归
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }


    /**
     * 递归查找叶子节点
     * @param sysMenu
     * @param treeNodes
     * @return
     */
    public static SysMenu findChildren(SysMenu sysMenu,List<SysMenu> treeNodes){
        sysMenu.setChildren(new ArrayList<SysMenu>());
//        遍历所有菜单数据 判断 id 和 parentId对应关系
        for (SysMenu it : treeNodes){
            if (sysMenu.getId().longValue() == it.getParentId().longValue()){
                if (sysMenu.getChildren() == null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }





}
