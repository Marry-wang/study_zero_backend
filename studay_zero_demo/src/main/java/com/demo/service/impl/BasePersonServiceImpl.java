package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mapper.BasePersonMapper;
import com.demo.model.BasePerson;
import com.demo.service.BasePersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BasePersonServiceImpl
 * @Author 王孟伟
 * @Date 2021/10/13 17:00
 * @Version 1.0
 */
@Slf4j
@Service
public class BasePersonServiceImpl implements BasePersonService {

    @Autowired
    private BasePersonMapper basePersonMapper;

    @Override
    public BasePerson getBasePerson(BasePerson basePerson) {
        BasePerson basePerson1 = basePersonMapper.selectOne(new QueryWrapper<BasePerson>().lambda()
                .eq(BasePerson::getPhone, basePerson.getPhone())
        );
        return basePerson1;
    }
}
