package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.mapper.SysMenuMapper;
import com.demo.domain.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/16 14:47
 * @Description:
 */
@Service
public class SysMenuServiceImpl  implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuPo> queryMenuList() {
        LambdaQueryWrapper<SysMenuPo> sysMenuPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return sysMenuMapper.selectList(sysMenuPoLambdaQueryWrapper);
    }
}
