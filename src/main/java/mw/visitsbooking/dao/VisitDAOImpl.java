package mw.visitsbooking.dao;

import java.time.LocalDateTime;
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

		Query<Visit> query = session.createQuery("from Visit order by date", Visit.class);

		List<Visit> visits = query.getResultList();

		return visits;

	}
	
	@Override
	public List<Visit> getVisitsPeriod(LocalDateTime start, LocalDateTime end) {

		Session session = sessionFactory.getCurrentSession();

		Query<Visit> query = session.createQuery("from Visit where date between :start and :end order by date", Visit.class);
		query.setParameter("start", start);
		query.setParameter("end", end);

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

		query = session.createQuery("from Visit where customer=:customer order by date", Visit.class);
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

	// Not using for present solution.
//	@Override
//	public List<Integer> getNumberOfVisits() {
//
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query query = session.createSQLQuery("select t1.v_number from (select c.id as c_id, count(v.id) as v_number from Customer c left join Visit v on c.id=v.customer_id group by c.id order by c.last_name) as t1");
//				
//		List<Integer> visitsNumber = query.getResultList();
//		
//		return visitsNumber;
//	}

}
