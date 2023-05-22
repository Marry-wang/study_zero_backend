package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.entry.dto.SysMenuDto;
import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.entry.po.SysRoleMenuPo;
import com.demo.domain.entry.po.SysUserPo;
import com.demo.domain.entry.vo.SysMenuVo;
import com.demo.domain.mapper.SysMenuMapper;
import com.demo.domain.mapper.SysRoleMenuMapper;
import com.demo.domain.mapper.SysUserMapper;
import com.demo.domain.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/16 14:47
 * @Description:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenuVo> queryMenuByTRoleId(Integer roleId) {
        LambdaQueryWrapper<SysRoleMenuPo> queryWrapper = new QueryWrapper<SysRoleMenuPo>().lambda()
                .eq(SysRoleMenuPo::getRoleId, roleId);
        List<SysRoleMenuPo> sysRoleMenuPos = sysRoleMenuMapper.selectList(queryWrapper);

        if(sysRoleMenuPos.size()>0){
            List<SysMenuPo> sysMenuPos = new ArrayList<>();
            sysRoleMenuPos.forEach(roleMenuPo -> {
                SysMenuPo sysMenuPo = sysMenuMapper.selectById(roleMenuPo.getMenuId());
                sysMenuPos.add(sysMenuPo);
            });

            ArrayList<SysMenuPo> menuParentList = new ArrayList<>();
            for (SysMenuPo sysMenuPo : sysMenuPos) {
                if(Objects.isNull(sysMenuPo.getParentId())){
                    menuParentList.add(sysMenuPo);
                }
            }
            List<SysMenuPo> meuTree = getMeuTree(menuParentList, sysMenuPos);
            //TODO 数据转换
        }
        return null;
    }

    @Override
    public List<SysMenuPo> queryMenuList() {
        LambdaQueryWrapper<SysMenuPo> sysMenuPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<SysMenuPo> sysMenuPos = sysMenuMapper.selectList(sysMenuPoLambdaQueryWrapper);

        ArrayList<SysMenuPo> menuParentList = new ArrayList<>();
        for (SysMenuPo sysMenuPo : sysMenuPos) {
            if(Objects.isNull(sysMenuPo.getParentId())){
                menuParentList.add(sysMenuPo);
            }
        }
        getMeuTree(menuParentList,sysMenuPos);
        return menuParentList;
    }

    private List<SysMenuPo> getMeuTree(List<SysMenuPo>parentList, List<SysMenuPo>menuList){
        for(SysMenuPo sysMenuPo:parentList){
            List<SysMenuPo> chridrenMenuList = new ArrayList<>();
            for (SysMenuPo sysMenuPo1:menuList){
                if(Objects.equals(sysMenuPo.getId(), sysMenuPo1.getParentId())){

                    chridrenMenuList.add(sysMenuPo1);
                }
            }
            List<SysMenuPo> meuTree = getMeuTree(chridrenMenuList, menuList);
            sysMenuPo.setChrldren(meuTree);
        }
        return parentList;
    }

    @Override
    public SysMenuPo selectMenu(SysMenuDto dto) {
        return sysMenuMapper.selectById(dto.getMenuId());
    }

    @Override
    public Boolean addMenu(SysMenuPo sysMenuPo) {
        return SqlHelper.retBool(sysMenuMapper.insert(sysMenuPo));
    }

    @Override
    public Boolean updateMenu(SysMenuPo sysMenuPo) {
        return SqlHelper.retBool(sysMenuMapper.updateById(sysMenuPo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delMenu(SysMenuDto dto) {
        //删除菜单  将对应的角色下的菜单进行删除
        LambdaQueryWrapper<SysRoleMenuPo> lambda = new QueryWrapper<SysRoleMenuPo>().lambda();
        lambda.eq(SysRoleMenuPo::getMenuId,dto.getMenuId());
        sysRoleMenuMapper.delete(lambda);
        return SqlHelper.retBool(sysMenuMapper.deleteById(dto.getMenuId()));
    }


}
