package com.demo.domain.login.service;

import com.demo.domain.login.entry.dto.LoginDto;

public interface LoginService {
    /**
     * 获取登录的token
     *
     * @param dto
     * @return
     */
    String getLoginToken(LoginDto dto);

    Object getLoginMessage();

    Boolean loginOut();
}
