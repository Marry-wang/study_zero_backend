package com.demo.service.impl;

import com.demo.mapper.BaseMapper;
import com.demo.model.BasePerson;
import com.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseMapper baseMapper;

    @Override
    public List<BasePerson> getBase() {
        return baseMapper.getBase();
    }
}
