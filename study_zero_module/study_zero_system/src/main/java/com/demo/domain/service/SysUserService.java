package com.demo.domain.service;

import com.demo.domain.entry.dto.SysUserDto;
import com.demo.domain.entry.dto.AddOrUpdateUserDto;
import com.demo.domain.entry.vo.SysUserRoleVo;
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
    List<SysUserRoleVo> queryUserList(SysUserDto dto);

    /**
     * 更新用户
     *
     * @param dto
     * @return
     */
    Boolean addOrUpdateUserRole(AddOrUpdateUserDto dto);

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    List<Integer> selectUserRole(Integer userId);

    /**
     * 删除用户
     *
     * @param dto
     * @return
     */
    Boolean delUser(SysUserDto dto);

}
