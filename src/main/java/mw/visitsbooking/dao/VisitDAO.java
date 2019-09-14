package mw.visitsbooking.dao;

import java.time.LocalDateTime;
import java.util.List;

import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;

public interface VisitDAO {
	
	public List<Visit> getVisits();
	
	List<Visit> getVisitsPeriod(LocalDateTime start, LocalDateTime end);
	
	public Visit getVisit(int id);
	
	public List<Visit> searchVisits(Customer customer);
	
	public void saveVisit(Visit visit);
	
	public void deleteVisit(int id);

}
