package cn.jiang.service;

import cn.jiang.result.Result;

public interface RedisService {

    Result reduceValue(String lockKey,String key,String value);
}
