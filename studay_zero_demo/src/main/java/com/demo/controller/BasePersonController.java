package com.demo.controller;

import com.demo.annotation.ZeroAnnotation;
import com.demo.model.BasePerson;
import com.demo.service.BasePersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BasePersonController
 * @Author 王孟伟
 * @Date 2021/10/13 17:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/base")
@Api(value = "获取基本信息", tags = {"获取基本信息"})
public class BasePersonController {

    @Autowired
    private BasePersonService basePersonService;

    @ZeroAnnotation
    @PostMapping("/person")
    @ApiOperation(value = "获取人员基本信息", notes = "获取人员基本信息")
//    public BasePerson getPerson(@RequestBody BasePerson basePerson){
    public BasePerson getPerson() {
        BasePerson basePerson = new BasePerson();
        basePerson.setPhone("11111111111");
        return basePersonService.getBasePerson(basePerson);
    }
}
