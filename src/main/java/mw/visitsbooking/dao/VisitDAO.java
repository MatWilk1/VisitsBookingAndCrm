package mw.visitsbooking.dao;

import java.util.List;

import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;

public interface VisitDAO {
	
	public List<Visit> getVisits();
	
	public Visit getVisit(int id);
	
	public List<Visit> searchVisits(Customer customer);
	
	public void saveVisit(Visit visit);
	
	public void deleteVisit(int id);

}
