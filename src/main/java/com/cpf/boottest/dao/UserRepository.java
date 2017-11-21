package com.cpf.boottest.dao;

import com.cpf.boottest.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by cpf on 2017/9/28.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity,String>
        ,JpaSpecificationExecutor<UserEntity> {
     UserEntity findByUsernameAndPassword(String username ,String password);
    UserEntity  findByUsername(String username);
}
