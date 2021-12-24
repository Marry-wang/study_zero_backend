package com.demo.config;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.javassist.Modifier;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Properties;


@Component
@Intercepts(
        {
                @org.apache.ibatis.plugin.Signature(
                        type = Executor.class,
                        method = "query",
                        args = {
                                MappedStatement.class,
                                Object.class,
                                RowBounds.class,
                                ResultHandler.class,
                                CacheKey.class,
                                BoundSql.class
                        }
                )
        }
)
public class MybatisOneInterceptor implements Interceptor {

    /*自定义SQL*/
    private String resetSql(String sql) {
        return sql +" limit 1";
    }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        resetSql(invocation);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }



    @Override
    public void setProperties(Properties properties) {

    }

    private void resetSql(Invocation invocation){
        final Object [] args =invocation.getArgs();
        BoundSql boundSql =(BoundSql) args[5];
        if(!StringUtils.isEmpty(boundSql.getSql())){
            modify(boundSql,"sql",resetSql(boundSql.getSql()));
        }
    }

    private static void modify(Object object, String fieldName, Object newFieldValue){
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            if(!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(object, newFieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
