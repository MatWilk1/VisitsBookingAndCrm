package mw.visitsbooking.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mw.visitsbooking.entity.Log;

@Repository
public class LogDAOImpl implements LogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Log> getLogs() {
		
		Session session = sessionFactory.getCurrentSession();

		Query<Log> query = session.createQuery("from Log order by date", Log.class);

		List<Log> logs = query.getResultList();

		return logs;
	}

	@Override
	public Log getLog(int id) {
		
		Session session = sessionFactory.getCurrentSession();

		Log log = session.get(Log.class, id);

		return log;
	}

	@Override
	public void saveLog(Log log) {
		
		Session session = sessionFactory.getCurrentSession();

		session.save(log);

	}

}
