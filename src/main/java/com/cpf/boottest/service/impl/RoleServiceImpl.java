package com.cpf.boottest.service.impl;

import com.cpf.boottest.dao.RoleRepository;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.service.RoleService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by cpf on 2017/11/20.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<RoleEntity> findRoleCriteria(Integer page, Integer size, RoleEntity roleEntity) {
        Pageable pa = new PageRequest(page, size);
        return roleRepository.findAll(new Specification<RoleEntity>() {
            @Override
            public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return dttj(roleEntity, root, query, cb);
            }
        }, pa);
    }

    @Override
    public RoleEntity findByRid(String rid) {

        return roleRepository.findOne(rid);
    }

    @Override
    public List<RoleEntity> findRolelist(RoleEntity roleEntity) {
        return roleRepository.findAll(new Specification<RoleEntity>() {
            @Override
            public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return dttj(roleEntity, root, query, cb);
            }
        });
    }

    @Override
    public void insert(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
    }

    @Override
    public void update(RoleEntity roleEntity) {
        roleRepository.saveAndFlush(roleEntity);
    }

    @Override
    public void delete(String rid) {
        roleRepository.delete(rid);
    }

    @Override
    public List<RoleEntity> findByRidIn(Collection<String> roles) {
        return roleRepository.findByRidIn(roles);
    }

    @Override
    public String getTree(RoleEntity roleEntity) {
        List<RoleEntity> rolelist = this.findRolelist(roleEntity);
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (RoleEntity role : rolelist) {
            map = new HashMap<String, Object>();

            map.put("id", role.getRid());
            map.put("pId", role.getZid());
            map.put("dId", role.getRid());
            map.put("name", role.getRname());
            long count_ = 0;
            roleEntity.setZid(role.getRid());
            count_ = roleRepository.count(new Specification<RoleEntity>() {
                @Override
                public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                    return dttj(roleEntity, root, query, cb);
                }
            });

            if (count_ > 0) {
                map.put("isParent", true);
            }
            listmap.add(map);
        }
        String json = JSONArray.toJSONString(listmap);
        return json;
    }

    public Predicate dttj(RoleEntity roleEntity, Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        if (roleEntity.getRname() != null && roleEntity.getRname() != "") {
            list.add(cb.equal(root.get("rname").as(String.class), roleEntity.getRname()));
        }
        if (roleEntity.getFunctions() != null && roleEntity.getFunctions() != "") {

            list.add(cb.like(root.get("functions").as(String.class), "%" + roleEntity.getFunctions() + "%"));
        }
        if (roleEntity.getZid() != null && roleEntity.getZid() != "") {
            list.add(cb.equal(root.get("zid").as(String.class), roleEntity.getZid()));
        }


        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }
}
