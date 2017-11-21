package com.cpf.boottest.service.impl;


import com.cpf.boottest.dao.BaseDao;
import com.cpf.boottest.dao.UserDao;
import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.UserService;
import com.cpf.boottest.util.commen.Splits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cpf on 2017/8/23.
 */
@Service
@Transactional
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseDao baseDao;

    @Override
    public UserEntity getByNumber(String number) {
        return userDao.getByUname(number);
    }

    @Override
    public List<String> findPermissionUrl(String username) {
        UserEntity user = this.getByNumber(username);
        String[] roless = new String[]{};
        String[] roles =Splits.getsumsz(roless,user.getRoleids().split("/"));
        String wherein =  "and rid in ("+Splits.getwherein(roles)+")" ;
        List<RoleEntity> rolelist = baseDao.getlistBy("RoleEntity","role",wherein);
        String[] funs = new String[]{};
        for(RoleEntity role : rolelist){
            funs =Splits.getsumsz(funs,role.getFunctions().split("/"));
        }
        String  whereinfun = "and mid in("+Splits.getnorepeatwherein(funs)+")";
        List<ModuleEntity> modulelist = baseDao.getlistBy("ModuleEntity","module",whereinfun);
        List<String>  permissionUrl = new ArrayList<String>();
        for (ModuleEntity module:modulelist) {
            permissionUrl.add(module.getMurl());
        }
         return  permissionUrl;
    }
}
