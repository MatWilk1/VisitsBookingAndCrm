package mw.visitsbooking.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;

@Repository
public class VisitDAOImpl implements VisitDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Visit> getVisits() {

		Session session = sessionFactory.getCurrentSession();

		Query<Visit> query = session.createQuery("from Visit order by id", Visit.class);

		List<Visit> visits = query.getResultList();

		return visits;

	}

	@Override
	public Visit getVisit(int id) {

		Session session = sessionFactory.getCurrentSession();

		Visit visit = session.get(Visit.class, id);

		return visit;
	}

	@Override
	public List<Visit> searchVisits(Customer customer) {

		Session session = sessionFactory.getCurrentSession();

		Query<Visit> query = null;

		query = session.createQuery("from Visit where customer=:customer", Visit.class);
		query.setParameter("customer", customer);

		List<Visit> visits = query.getResultList();

		return visits;
	}

	@Override
	public void saveVisit(Visit visit) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(visit);

	}

	@Override
	public void deleteVisit(int id) {

		Session session = sessionFactory.getCurrentSession();
		
		Visit visit = session.get(Visit.class, id);
		
		session.delete(visit);

	}

}
