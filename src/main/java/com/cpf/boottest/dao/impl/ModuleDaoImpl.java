package com.cpf.boottest.dao.impl;

import com.cpf.boottest.dao.ModuleDao;
import com.cpf.boottest.pojo.ModuleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/**
 * Created by cpf on 2017/9/25.
 */
@Repository(value = "ModuleDao")
public class ModuleDaoImpl implements ModuleDao {
    @PersistenceContext
    private EntityManager entityManager;













}
