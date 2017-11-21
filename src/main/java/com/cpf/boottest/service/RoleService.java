package com.cpf.boottest.service;

import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * Created by cpf on 2017/9/11.
 */
public interface RoleService {
    Page<RoleEntity> findRoleCriteria(Integer page, Integer size, RoleEntity roleEntity);

    RoleEntity findByRid(String rid);

    List<RoleEntity> findRolelist(RoleEntity roleEntity);

    void insert(RoleEntity roleEntity);

    void update(RoleEntity roleEntity);

    void delete(String rid);
    List<RoleEntity> findByRidIn(Collection<String> roles );
    String getTree(RoleEntity roleEntity);
}
