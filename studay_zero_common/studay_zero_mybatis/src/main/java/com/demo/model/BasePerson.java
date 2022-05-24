package com.demo.model;

import lombok.Data;

/**
 * @ClassName BasePerson
 * @Author 王孟伟
 * @Date 2021/10/13 16:53
 * @Version 1.0
 */
@Data
public class BasePerson {

    private Integer id;

    private String username;

    private String phone;

    private String salt;

    private String password;


}
