package com.cpf.boottest.dao;

import com.cpf.boottest.pojo.UserEntity;

import java.util.List;

/**
 * Created by cpf on 2017/9/25.
 */
public interface BaseDao {
    public <T> T findObj(Class T,int id);

    public <T>List<T>   getlistBy(String  T , String  t,String where );

    boolean addUser(Object obj);

    boolean deleteTbyid(Class T,int id);

    boolean updateUser(Object obj);
}
