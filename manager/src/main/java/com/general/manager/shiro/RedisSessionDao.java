package com.general.manager.shiro;

import com.general.manager.conts.RedisConstans;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 把Shiro的session放到redis
 */
@Component
public class RedisSessionDao extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
    @Value("${shiro.globalSessionTimeout}")
    private Integer expireTime;

    private static String prefix = RedisConstans.REDIS_SHIRO_SESSION;

    public RedisSessionDao() {
        super();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 创建session ,保存到redis
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        logger.debug("创建了session:{}", session.getId());
        try {
            String key = prefix + sessionId.toString();
            redisTemplate.opsForValue().set(key, session, expireTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("redis获取session异常" + e.getMessage(), e);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        logger.debug("get session:{}", serializable.toString());
        Session session = null;
        try {
            String key = prefix + serializable.toString();
            session = (Session) redisTemplate.opsForValue().get(key);
            if (session == null) {
                redisTemplate.boundValueOps(key).expire(expireTime, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            logger.error("can not get session from redis: " + e.getMessage(), e);
        }
        return session;
    }

    /**
     * 更新session
     *
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        logger.debug("更新session:{}", session.getId());
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
            String key = prefix + session.getId();
            redisTemplate.opsForValue().set(key, session, expireTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("redis update error:{}" + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Session session) {
        logger.debug("delete session:{}", session.getId());
        try {
            String key = prefix + session.getId();
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("delete key error:{}" + e.getMessage(), e);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.info("get alived session");
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = redisTemplate.keys(sessions);
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                Session session = (Session) redisTemplate.opsForValue().get(key);
                sessions.add(session);
            }
        }
        return sessions;
    }
}
