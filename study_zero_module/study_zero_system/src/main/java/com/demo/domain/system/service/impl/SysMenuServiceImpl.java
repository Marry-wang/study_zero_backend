package com.demo.domain.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.context.SecurityContextHolder;
import com.demo.domain.system.entry.dto.AddOrUpdateMenuDto;
import com.demo.domain.system.entry.dto.SysMenuDto;
import com.demo.domain.system.entry.po.SysMenuPo;
import com.demo.domain.system.entry.po.SysRoleMenuPo;
import com.demo.domain.system.entry.vo.SysMenuVo;
import com.demo.domain.system.mapper.SysMenuMapper;
import com.demo.domain.system.mapper.SysRoleMenuMapper;
import com.demo.domain.system.service.SysMenuService;
import com.demo.enums.BaseResultEnum;
import com.demo.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private SysUserServiceImpl userService;

    @Override
    public List<SysMenuVo> queryMenuList() {

        List<SysMenuPo> sysMenuPos = new ArrayList<>();
        ArrayList<SysMenuVo> menuParentList = new ArrayList<>();

        String userId = SecurityContextHolder.get("id");
        List<Integer> roleIds = userService.selectUserRole(Integer.parseInt(userId));

        if(CollectionUtil.isEmpty(roleIds)){
            return menuParentList;
        }
        List<SysRoleMenuPo> sysRoleMenuPos = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenuPo>().lambda()
                .in(SysRoleMenuPo::getRoleId, roleIds));

        List<Integer> menuIds = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(sysRoleMenuPos)){
            menuIds = sysRoleMenuPos.stream().map(SysRoleMenuPo::getMenuId).collect(Collectors.toList());
        }else{
            return menuParentList;
        }


        LambdaQueryWrapper<SysMenuPo> sysMenuPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuPoLambdaQueryWrapper.in(SysMenuPo::getId,menuIds);
        sysMenuPos = sysMenuMapper.selectList(sysMenuPoLambdaQueryWrapper);


        for (SysMenuPo sysMenuPo : sysMenuPos) {
            if (Objects.isNull(sysMenuPo.getParentId())) {
                SysMenuVo sysMenuVo = new SysMenuVo();
                sysMenuVo.setId(sysMenuPo.getId());
                sysMenuVo.setMenuName(sysMenuPo.getMenuName());
                sysMenuVo.setPath(sysMenuPo.getPath());
                sysMenuVo.setIcon(sysMenuPo.getIcon());
                sysMenuVo.setChildren(sysMenuPo.getChildren());
                menuParentList.add(sysMenuVo);
            }
        }
        getMeuTree(menuParentList, sysMenuPos);
        return menuParentList;
    }

    private List<SysMenuVo> getMeuTree(List<SysMenuVo> parentList, List<SysMenuPo> menuList) {
        for (SysMenuVo sysMenuVo : parentList) {
            List<SysMenuVo> chridrenMenuList = new ArrayList<>();
            for (SysMenuPo sysMenuPo1 : menuList) {
                if (Objects.equals(sysMenuVo.getId(), sysMenuPo1.getParentId())) {
                    SysMenuVo sysMenuVo1 = new SysMenuVo();
                    sysMenuVo1.setId(sysMenuPo1.getId());
                    sysMenuVo1.setMenuName(sysMenuPo1.getMenuName());
                    sysMenuVo1.setPath(sysMenuPo1.getPath());
                    sysMenuVo1.setIcon(sysMenuPo1.getIcon());
                    sysMenuVo1.setChildren(sysMenuPo1.getChildren());
                    chridrenMenuList.add(sysMenuVo1);
                }
            }
            List<SysMenuVo> meuTree = getMeuTree(chridrenMenuList, menuList);
            if (meuTree.size() > 0) {
                sysMenuVo.setChildren(meuTree);
            }
        }
        return parentList;
    }

    @Override
    public Boolean addOrUpdateMenu(AddOrUpdateMenuDto dto) {
        SysMenuPo sysMenuPo = new SysMenuPo();
        BeanUtil.copyProperties(dto, sysMenuPo);
        if (ObjectUtil.isNotNull(sysMenuPo.getId())) {
            return SqlHelper.retBool(sysMenuMapper.updateById(sysMenuPo));
        } else {
            return SqlHelper.retBool(sysMenuMapper.insert(sysMenuPo));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delMenu(SysMenuDto dto) {
        //删除菜单  将对应的角色下的菜单进行删除
        LambdaQueryWrapper<SysRoleMenuPo> lambda = new QueryWrapper<SysRoleMenuPo>().lambda();
        lambda.eq(SysRoleMenuPo::getMenuId, dto.getMenuId());
        sysRoleMenuMapper.delete(lambda);

        LambdaQueryWrapper<SysMenuPo> wrapper = new QueryWrapper<SysMenuPo>().lambda();
        wrapper.eq(SysMenuPo::getParentId, dto.getMenuId());
        List<SysMenuPo> sysMenuPos = sysMenuMapper.selectList(wrapper);
        if (sysMenuPos.size() > 0) {
            throw new BaseException(BaseResultEnum.SYSTEM_MENU_IS_USER);
        }

        return SqlHelper.retBool(sysMenuMapper.deleteById(dto.getMenuId()));
    }


}
