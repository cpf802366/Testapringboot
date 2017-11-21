package com.cpf.boottest.service;

/**
 * Created by cpf on 2017/11/18.
 */
public interface RedisService {
    <T> T  put(String key, T value);
    <T> T  get(Class<T> clazz,String key   );

}
