package mw.visitsbooking.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mw.visitsbooking.dao.VisitDAO;
import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDAO visitDAO;

	@Override
	@Transactional
	public List<Visit> getVisits() {

		return visitDAO.getVisits();
	}

	@Override
	@Transactional
	public Visit getVisit(int id) {

		return visitDAO.getVisit(id);
	}

	@Override
	@Transactional
	public List<Visit> searchVisits(Customer customer) {

		return visitDAO.searchVisits(customer);
	}

	@Override
	@Transactional
	public void saveVisit(Visit visit) {

		visitDAO.saveVisit(visit);

	}

	@Override
	@Transactional
	public void deleteVisit(int id) {

		visitDAO.deleteVisit(id);

	}

	// Return all free visit dates (date and hour) in time scope when booking is available (one month)
	@Override
	@Transactional
	public List<LocalDateTime> getPossibleVisitTerms() {

		int visitFrequency = 60; // in minutes

		List<LocalDateTime> terms = new ArrayList<>();

		LocalDate today = LocalDate.now();

		// Possible booking for one month forward
		for (int i = 0; i <= 30; i++) {
			// First visit can be book today one hour since now
			// Visits starts at 9:00 ...
			LocalDateTime startVisits = today.plusDays(i).atTime(9, 0);
			
			if(!startVisits.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !startVisits.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				// ... every hour
				for (int j = 0; j <= 450; j += visitFrequency) {
					terms.add(startVisits.plusMinutes(j));
				}
			}
		}

		System.out.println(terms);

		// Check which visit terms already passed
		List<LocalDateTime> allPassedTodayTerms = new ArrayList<>();

		LocalDateTime startVisits = today.atTime(9, 0);

		// Time between start of first visit till now plus 1 hour (can't book visit few minutes before start)
		Duration timeFromBegToNow = Duration.between(startVisits, LocalDateTime.now().plusHours(1));
		long minutesFromBegToNow = timeFromBegToNow.toMinutes();
		System.out.println(minutesFromBegToNow);

		for (int j = 0; j < minutesFromBegToNow; j += visitFrequency) {
			allPassedTodayTerms.add(startVisits.plusMinutes(j));
		}

		System.out.println(allPassedTodayTerms);

		// Take all already booked terms
		List<Visit> visits = visitDAO.getVisits();
		List<LocalDateTime> bookedTerms = new ArrayList<>();
		for (Visit visit : visits) {
			bookedTerms.add(visit.getDate());
		}

		System.out.println(bookedTerms);

		// Remove from all terms today passed terms and already booked terms
		terms.removeAll(allPassedTodayTerms);
		terms.removeAll(bookedTerms);
		List<LocalDateTime> possibleTerms = terms;

		System.out.println(possibleTerms);

		return possibleTerms;
	}

}
