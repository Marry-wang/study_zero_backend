package com.demo.backend.securityService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.backend.mapper.BaseRoleMapper;
import com.demo.backend.mapper.UserRoleMapper;
import com.demo.backend.model.BasePerson;
import com.demo.backend.model.BaseRole;
import com.demo.backend.model.UserRole;
import com.demo.backend.service.BasePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomUserDetailService
 * @Author 王孟伟
 * @Date 2021/11/11 10:02
 * @Version 1.0
 */
@Component("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private BasePersonService basePersonService;

    @Autowired
    private BaseRoleMapper baseRoleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //查询用户
        BasePerson personByName = basePersonService.getPersonByName(name);
        if(ObjectUtils.isEmpty(personByName)){
            throw new UsernameNotFoundException("User"+name+"was not found in db");
        }
        UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().lambda()
                .eq(UserRole::getUserId, personByName.getId())
        );

        BaseRole baseRole = baseRoleMapper.selectOne(new QueryWrapper<BaseRole>().lambda()
                .eq(BaseRole::getId, userRole.getRoleId())
        );
        //设置角色
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(baseRole.getRole()));
        User user = new User(
                name, personByName.getPassword(), grantedAuthorities
        );
        System.out.println(user);
        return user;
    }
}
