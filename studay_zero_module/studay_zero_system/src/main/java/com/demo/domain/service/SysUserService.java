package com.demo.domain.service;

import com.demo.domain.entry.po.SysUserPo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:51
 * @Description:
 */
@Service
public interface SysUserService {

    /**
     * 查询用户列表
     *
     * @return
     */
    List<SysUserPo> queryUserList();


    /**
     * 新增用户
     *
     * @param sysUserPo
     * @return
     */
    Integer addUser(SysUserPo sysUserPo);

    /**
     * 更新用户
     *
     * @param sysUserPo
     * @return
     */
    Integer updateUser(SysUserPo sysUserPo);

    /**
     * 查询用户
     *
     * @param sysUserPo
     * @return
     */
    SysUserPo selectUser(SysUserPo sysUserPo);

    /**
     * 删除用户
     *
     * @param sysUserPo
     * @return
     */
    Integer delUser(SysUserPo sysUserPo);
}
