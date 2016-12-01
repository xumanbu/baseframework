package com.cxypub.baseframework.sdk.security.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cxypub.baseframework.sdk.cache.SimpleCache;

/***
 * @ClassName: MemcachedSessionDao
 * @Description: session保存
 * @author Comsys-徐飞
 * @date 2015年11月30日 下午4:31:50
 *
 */
public class MemcacheSessionDao extends AbstractSessionDAO {

	private final static Logger log = Logger.getLogger(MemcacheSessionDao.class);

	@Autowired
	@Qualifier("memcacheClient")
	private SimpleCache simpleCache;

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		try {
			simpleCache.add(sessionId.toString(), session, (int) session.getTimeout());
			// log.info("doCreate：" + simpleCache.get(sessionId.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;
		try {
			Object cacheSession = simpleCache.get(sessionId.toString());
			if (cacheSession != null && cacheSession instanceof Session) {
				session = (Session) cacheSession;
			}
			// log.info("doReadSession" + simpleCache.get(sessionId.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return session;
	}

	@Override
	public void delete(Session session) {
		try {
			simpleCache.delete(session.getId().toString());
			// log.info("doDeleteSession" + simpleCache.get(session.getId().toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return null;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
			simpleCache.add(session.getId().toString(), session, (int) session.getTimeout());
			// log.info("doUpdateSession" + simpleCache.get(session.getId().toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
