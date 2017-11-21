package com.cpf.boottest.service.impl;

import com.cpf.boottest.dao.ModuleRepository;
import com.cpf.boottest.dao.RoleRepository;
import com.cpf.boottest.dao.UserRepository;
import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.UserReService;
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
 * Created by cpf on 2017/9/28.
 */
@Service(value = "UserReService")
@Transactional
public class UserReServiceImpl implements UserReService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository  roleRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    // 分页查询 动态条件
    public Page<UserEntity> findUserCriteria(Integer page, Integer size, UserEntity userquery) {
        Pageable pa = new PageRequest(page, size);
        Page<UserEntity> pu = userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(userquery.getUsername() != null){
                    list.add(cb.equal(root.get("username").as(String.class), userquery.getUsername()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        }, pa);
       /* pu.getContent().size();
        pu.getContent().get(0);*/
            /*无条件分页查询*/
        /*userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        });*/
        return pu;
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(  username,   password);
    }

    @Override
    public List<RoleEntity> findRolelist (String username) {
        UserEntity byRoleids = userRepository.findByUsername(username);
        String[] roles = byRoleids.getRoleids().split("/");
        List<String> rolelist = Arrays.asList(roles);
//         String[] arr = rolelist.toArray(new String[rolelist.size()]);
        List<RoleEntity>   roleidins = roleRepository.findByRidIn(rolelist);
        return roleidins;
    }

    @Override
    public List<ModuleEntity> findPermissionlist(String username) {

        List<RoleEntity>   roleidins = findRolelist ( username);
        Set<String>  perids = new HashSet<String>();
        for (RoleEntity role:roleidins) {
            List<String> strings = Arrays.asList(role.getFunctions().split("/"));
            perids.addAll(strings);
        }
        List<ModuleEntity> byMidIn = moduleRepository.findByMidIn(perids);

        return byMidIn;
    }


}
