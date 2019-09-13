package mw.visitsbooking.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mw.visitsbooking.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		Session session = sessionFactory.getCurrentSession();

		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public Customer getCustomer(int id) {

		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, id);

		return customer;
	}

	// searching by firstName and lastName
	@Override
	public List<Customer> searchCustomers(String name) {

		Session session = sessionFactory.getCurrentSession();

		Query<Customer> query = null;

		if (name != null) {
			query = session.createQuery(
					"from Customer where lower(firstName) like :name or lower(lastName) like :name order by lastName",
					Customer.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer order by lastName", Customer.class);
		}

		List<Customer> customers = query.getResultList();

		return customers;
	}

	// Searching if customer with the same fields exists (the same firstName and lastName and (phone or e-mail))
	@Override
	public int getIdIfExists(Customer customer) {

		Session session = sessionFactory.getCurrentSession();

		Query<Customer> query = null;

		query = session.createQuery(
				"from Customer where lower(firstName)=:firstName and lower(lastName)=:lastName and (phoneNumber=:phoneNumber or lower(email)=:email)",
				Customer.class);
		query.setParameter("firstName", customer.getFirstName().toLowerCase());
		query.setParameter("lastName", customer.getLastName().toLowerCase());
		query.setParameter("phoneNumber", customer.getPhoneNumber());
		query.setParameter("email", customer.getEmail().toLowerCase());
		
		List<Customer> customers = query.getResultList();
		
		if(customers.size() > 0) {
			return customers.get(0).getId();
		}else {
			return 0;
		}

	}

	@Override
	public void saveCustomer(Customer customer) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {

		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, id);

		session.delete(customer);
	}

}
