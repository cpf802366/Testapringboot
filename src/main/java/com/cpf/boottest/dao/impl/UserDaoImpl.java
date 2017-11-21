package com.cpf.boottest.dao.impl;


import com.cpf.boottest.dao.UserDao;
import com.cpf.boottest.pojo.UserEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by cpf on 2017/8/23.
 */
@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public UserEntity getByUname(String uname) {
        // 分页
        DetachedCriteria da = DetachedCriteria.forClass(UserEntity.class);
        da.add(Restrictions.eq("name", uname));
        HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
        Session session = hEntityManager.getSession();
        da.getExecutableCriteria(session).setFirstResult(1).setMaxResults(5).list();
        Query query = this.entityManager.createQuery(" select user from UserEntity as user where username=:username",UserEntity.class);
         query.setParameter("username", uname);
        List<UserEntity> rs   = query.getResultList();
        if(rs.size()>0){
            return  rs.get(0);
        }else{
            return  null;
        }

    }





}
