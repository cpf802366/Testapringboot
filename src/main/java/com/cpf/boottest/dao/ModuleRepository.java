package com.cpf.boottest.dao;

import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by cpf on 2017/11/18.
 */
@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity,String>
        ,JpaSpecificationExecutor<ModuleEntity> {
         List<ModuleEntity> findByMidIn(Collection<String> mids);
}

