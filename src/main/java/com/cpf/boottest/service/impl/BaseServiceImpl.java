package com.cpf.boottest.service.impl;

import com.cpf.boottest.dao.BaseDao;
import com.cpf.boottest.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cpf on 2017/9/26.
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean addUser(Object obj) {
        return baseDao.addUser(obj);
    }

    @Override
    public <T> T findObj(Class T, int id) {
        return baseDao.findObj(T,id);
    }

    @Override
    public <T> List<T> getlistBy(String T, String t, String where) {
        return baseDao.getlistBy(T,t,where);
    }

    @Override
    public boolean deleteTbyid(Class T, int id) {
        return baseDao.deleteTbyid(T,id);
    }

    @Override
    public boolean updateUser(Object obj) {
        return baseDao.updateUser(obj);
    }
}
