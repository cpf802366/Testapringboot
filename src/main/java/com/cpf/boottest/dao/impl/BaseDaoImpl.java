package com.cpf.boottest.dao.impl;

import com.cpf.boottest.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by cpf on 2017/9/25.
 */
@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> T findObj(Class T, int id) {

        return (T)entityManager.find(T,id);
    }

    @Override
    public <T> List<T> getlistBy(String T,  String  t,String where) {
        String sql = " select  "+t+" from "+T+" as "+t+"  where  1=1  "+where;
        return entityManager.createQuery(sql).getResultList();
    }

    @Override
    public boolean addUser(Object obj) {
         try{
             this.entityManager.persist(obj);
              return true;
         }catch(Exception ex){
             ex.printStackTrace();
             return false;
         }

    }

    @Override
    public boolean deleteTbyid(Class T, int id) {
        try{
        this.entityManager.remove(this.findObj(T,id));
              return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(Object obj) {
        try{
            this.entityManager.merge(obj);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }

    }
}
