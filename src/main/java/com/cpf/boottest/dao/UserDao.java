package com.cpf.boottest.dao;


import com.cpf.boottest.pojo.UserEntity;

import java.util.List;

/**
 * Created by cpf on 2017/8/23.
 */
public interface UserDao {


    UserEntity getByUname(String uname);



}
