package com.demo.backend.controller;

import com.demo.annotation.ZeroAnnotation;
import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultFactory;
import com.demo.backend.mapper.AddMysqlMapper;
import com.demo.backend.model.AddMysql;
import com.demo.backend.model.BasePerson;
import com.demo.backend.service.BasePersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @Autowired
    private AddMysqlMapper addMysqlMapper;

    @ZeroAnnotation
    @PostMapping("/person")
    @ApiOperation(value = "获取人员基本信息", notes = "获取人员基本信息")
//    public BasePerson getPerson(@RequestBody BasePerson basePerson){
    public Result getPerson() {
        BasePerson basePerson = new BasePerson();
        basePerson.setPhone("11111111111");
//        int a =0;
//        int b=1;
//        int c =b/a;
        return ResultFactory.buildSuccess(basePersonService.getBasePerson(basePerson));
    }

    @PostMapping("/add")
    public void add() {
        for(int i=932451;i<1000000;i++){
            try {
                AddMysql addMysql = new AddMysql();
                addMysql.setName("张三"+i);
                addMysql.setEmail(i+"@qq.com");
                addMysql.setHome("大街"+i);
                addMysql.setIdcard(i+"");
                addMysql.setPhone(i+"");
                addMysql.setSchool("第"+i+"大学");
                addMysql.setCreateBy("admin");
                addMysql.setCreateTime(new Date());
                addMysqlMapper.insert(addMysql);
            }catch (Exception e){
                System.out.printf(e.toString());
            }

        }
    }
}
