package com.demo.controller;

import com.demo.model.BasePerson;
import com.demo.service.BasePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
public class BasePersonController {

    @Autowired
    private BasePersonService basePersonService;

    @RequestMapping("/person")
//    public BasePerson getPerson(@RequestBody BasePerson basePerson){
    public BasePerson getPerson() {
        BasePerson basePerson = new BasePerson();
        basePerson.setPhone("11111111111");
        return basePersonService.getBasePerson(basePerson);
    }
}
