package com.cpf.boottest.service;

import java.util.List;

/**
 * Created by cpf on 2017/9/26.
 */
public interface BaseService   {
    public <T> T findObj(Class T,int id);

    public <T>List<T> getlistBy(String  T , String  t, String where );

    boolean addUser(Object obj);

    boolean deleteTbyid(Class T,int id);

    boolean updateUser(Object obj);
}
