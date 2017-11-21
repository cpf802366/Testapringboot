package com.cpf.boottest.service;

import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * Created by cpf on 2017/9/28.
 */
public interface UserReService {
    Page<UserEntity> findUserCriteria(Integer page, Integer size, UserEntity userquery);

    UserEntity findByUsernameAndPassword(String username ,String password);
    List<RoleEntity> findRolelist (String username);
       List<ModuleEntity> findPermissionlist(String username) ;


}
