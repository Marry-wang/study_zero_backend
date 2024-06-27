package com.demo.metaObjectHandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.demo.context.SecurityContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StudyZeroMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createBy", Integer.parseInt(SecurityContextHolder.get("createId")),metaObject);
        this.setFieldValByName("createTime", new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", Integer.parseInt(SecurityContextHolder.get("createId")),metaObject);
        this.setFieldValByName("updateTime", new Date(),metaObject);
    }
}
