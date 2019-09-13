package mw.visitsbooking.service;

import java.time.LocalDateTime;
import java.util.List;

import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;

public interface VisitService {
	
	public List<Visit> getVisits();
	
	public Visit getVisit(int id);
	
	public List<Visit> searchVisits(Customer customer);
	
	public void saveVisit(Visit visit);
	
	public void deleteVisit(int id);
	
	public List<LocalDateTime> getPossibleVisitTerms();

}
