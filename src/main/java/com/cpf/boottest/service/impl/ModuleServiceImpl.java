package com.cpf.boottest.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cpf.boottest.dao.ModuleRepository;
import com.cpf.boottest.dao.RoleRepository;
import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.service.ModuleService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpf on 2017/11/21.
 */
@Service
@Transactional
public class ModuleServiceImpl  implements ModuleService{
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private RoleRepository repository;
    @Override
    public Page<ModuleEntity> findmoduleCriteria(Integer page, Integer size, ModuleEntity moduleEntity) {
        Pageable pa = new PageRequest(page, size);
        return moduleRepository.findAll(new Specification<ModuleEntity>() {
            @Override
            public Predicate toPredicate(Root<ModuleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return dttj(moduleEntity, root, query, cb);
            }
        }, pa);
    }

    @Override
    public ModuleEntity findByMid(String mid) {
        return moduleRepository.findOne(mid);
    }

    @Override
    public List<ModuleEntity> findModulelist(ModuleEntity moduleEntity) {
        return moduleRepository.findAll(new Specification<ModuleEntity>() {
            @Override
            public Predicate toPredicate(Root<ModuleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return dttj(moduleEntity, root, query, cb);
            }
        });
    }

    @Override
    public void insert(ModuleEntity moduleEntity) {
        moduleRepository.save(moduleEntity);
    }

    @Override
    public void update(ModuleEntity moduleEntity) {
        moduleRepository.saveAndFlush(moduleEntity);
    }

    @Override
    public void delete(String mid) {
     moduleRepository.delete(mid);
    }

    @Override
    public String getTree(ModuleEntity moduleEntity) {
        List<ModuleEntity> modulelist = this.findModulelist(moduleEntity);
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (ModuleEntity module : modulelist) {
            map = new HashMap<String, Object>();

            map.put("id", module.getMid());
            map.put("pId", module.getZid());
            map.put("dId", module.getMid());
            map.put("name", module.getMname());
            long count_ = 0;
            moduleEntity.setZid(module.getMid());
            count_ = moduleRepository.count(new Specification<ModuleEntity>() {
                @Override
                public Predicate toPredicate(Root<ModuleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                    return dttj(moduleEntity, root, query, cb);
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


    public Predicate dttj(ModuleEntity moduleEntity, Root<ModuleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        if (moduleEntity.getMid() != null && moduleEntity.getMid() != "") {
            list.add(cb.equal(root.get("mid").as(String.class), moduleEntity.getMid()));
        }
        if (moduleEntity.getMname() != null &&moduleEntity.getMname()  != "") {

            list.add(cb.like(root.get("mname").as(String.class), "%" + moduleEntity.getMname()  + "%"));
        }
        if (moduleEntity.getType() != null && moduleEntity.getType()!= "") {
            list.add(cb.equal(root.get("type").as(String.class),moduleEntity.getType()));
        }
        if (moduleEntity.getZid() != null && moduleEntity.getZid()!= "") {
            list.add(cb.equal(root.get("zid").as(String.class),moduleEntity.getZid()));
        }


        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }
}
