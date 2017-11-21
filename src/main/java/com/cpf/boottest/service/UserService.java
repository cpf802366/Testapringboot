package com.cpf.boottest.service;


import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.UserEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by cpf on 2017/8/23.
 */
public interface UserService {


    UserEntity getByNumber(String number);

    List<String> findPermissionUrl(String username);





}
