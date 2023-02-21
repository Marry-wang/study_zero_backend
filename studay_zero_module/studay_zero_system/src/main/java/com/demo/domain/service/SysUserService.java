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
     * 查询橘色列表
     * @return
     */
    List<SysUserPo> queryUserList();
}
