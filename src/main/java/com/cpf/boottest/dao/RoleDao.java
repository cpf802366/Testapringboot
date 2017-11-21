package com.cpf.boottest.dao;

import com.cpf.boottest.pojo.RoleEntity;

/**
 * Created by cpf on 2017/9/25.
 */
public interface RoleDao {

    RoleEntity getByRid(int rid);

    RoleEntity getByUname(String uname);

    int addUser(RoleEntity role);

    void deleteRoleByRid(int Rid);

    RoleEntity updateUser(RoleEntity roel);
}
