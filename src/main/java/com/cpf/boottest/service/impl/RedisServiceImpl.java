package com.cpf.boottest.service.impl;

import com.cpf.boottest.service.RedisService;
import com.cpf.boottest.util.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by cpf on 2017/11/18.
 */
@Service
@Transactional
public class RedisServiceImpl implements RedisService {

  @Autowired
  private RedisTemplate redisTemplate;
    @Override
    public <T> T put(String key, T value) {

        if(key == null || value == null) {
            return null;
        }

        byte[] bkey = SerializeUtils.serialize(key) ;
        redisTemplate.opsForValue().set(bkey, value);
        return value;

    }

    @Override
    public <T> T get(Class<T> clazz, String key) {
        if(key == null) {
            return null;
        }
        byte[] bkey =SerializeUtils.serialize(key);
        return  (T)redisTemplate.opsForValue().get(bkey);
    }


}
