package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.entry.vo.SysMenuVo;
import com.demo.domain.mapper.SysMenuMapper;
import com.demo.domain.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
