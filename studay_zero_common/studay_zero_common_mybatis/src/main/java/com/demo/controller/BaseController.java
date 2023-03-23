package com.demo.controller;

import com.demo.model.BasePerson;
import com.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @GetMapping("/getBase")
    public List<BasePerson> getBase(){
        return baseService.getBase();
    }
}
