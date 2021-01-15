package com.example.webdemo.common.redis;

import io.lettuce.core.RedisException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * redis锁
 * @author tangaq
 * @date 2020/12/4
 */
@Component
public class RedisLockUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisLockUtil.class);

    @Autowired
    private RedisCore redisCore;

    public boolean tryLock(long expireTime, String... keys) {
        String lockId = "";
        for (int i = 0; i < keys.length; i++) {
            lockId = lockId + keys[i] + "/";
        }
        return tryLock(lockId, expireTime);
    }

    /**
     * redis锁
     *
     * @param lockId
     * @param expireTime 单位(s)
     * @return true加锁成功/false加锁失败
     */
    public boolean tryLock(String lockId, long expireTime) {
        log.info("redis加锁开始:[{}]", lockId);
        if (expireTime <= 0) {
            return false;
        }
        boolean lockFlag = redisCore.setIfAbsent(lockId, lockId, expireTime);
        if (!lockFlag) {
            throw new RedisException("redis加锁失败:[" + lockId + "],重复");
        }
        try {
            redisCore.expire(lockId, expireTime);
        } catch (Exception e) {
            log.error("redis加锁:[{}]异常,errMsg:{}", lockId, e);
            throw new RedisException("redis加锁异常[" + lockId + "]");
        }
        log.info("redis加锁:[{}]成功,超时时间{}s", lockId, expireTime);
        return true;
    }

    public boolean unLock(String... lockIds) {
        String lockId = "";
        for (int i = 0; i < lockIds.length; i++) {
            lockId = lockId + lockIds[i] + "/";
        }
        return unLock(lockIds);
    }

    /**
     * 释放锁
     * @param lockId
     * @return
     */
    public boolean unLock(String lockId) {
        log.info("释放redis锁:[{}]", lockId);
        if (StringUtils.isBlank(lockId)) {
            return false;
        }
        redisCore.del(lockId);
        return true;
    }
}
