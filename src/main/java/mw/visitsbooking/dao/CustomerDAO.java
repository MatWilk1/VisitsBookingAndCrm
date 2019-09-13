package mw.visitsbooking.dao;

import java.util.List;

import mw.visitsbooking.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
	
	public Customer getCustomer(int id);
	
	// searching by firstName and lastName
	public List<Customer> searchCustomers(String name);
	
	public void saveCustomer(Customer customer);
	
	public void deleteCustomer(int id);

	int getIdIfExists(Customer customer);

}
