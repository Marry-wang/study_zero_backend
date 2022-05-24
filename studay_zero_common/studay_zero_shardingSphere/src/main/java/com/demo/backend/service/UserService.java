package com.demo.backend.service;

import com.demo.backend.model.User;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户信息
     */
    List<User> list();

    /**
     *   批量 保存用户信息
     * @param userVOList
     */
    String  insertForeach(List<User> userVOList);
}
