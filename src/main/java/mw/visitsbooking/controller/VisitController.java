package mw.visitsbooking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;
import mw.visitsbooking.service.CustomerService;
import mw.visitsbooking.service.VisitService;

@Controller
@RequestMapping("/visit")
public class VisitController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VisitService visitService;

	// Remove white spaces in form
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/visitForm")
	public String showVisitForm(Model model) {

		Visit visit = new Visit();

		// Set drop-down lists
		visit.setDateOptions(getPossibleVisitDates(visitService.getPossibleVisitTerms()));
		visit.setTimeOptions(visit.initTimeOptions());

		model.addAttribute("visit", visit);

		return "visit-form";
	}

	@PostMapping("/processVisitForm")
	public String processVisitForm(@Valid @ModelAttribute("visit") Visit visit, BindingResult bindingResult, Model model) {

		// Create date using chosen date and time and check if date is free
		boolean bookedDate = visit.createDate(visitService.getPossibleVisitTerms());

		System.out.println("Binding result: " + bindingResult);

		Customer customer = visit.getCustomer();
		customer.setVisits(Arrays.asList(visit));

		// Check if there are result error or if chosen term is already booked 
		if (bindingResult.hasErrors() || bookedDate) {
			visit.setDateOptions(getPossibleVisitDates(visitService.getPossibleVisitTerms()));
			visit.setTimeOptions(visit.initTimeOptions());
			
			List<LocalTime> freeTerms = new ArrayList<>();
			freeTerms = getFreeVisitHours(visitService.getPossibleVisitTerms(), visit.getChosenDate());
			model.addAttribute("free", freeTerms);

			return "visit-form";
		} else {
			// Check if customer with the same firstName and lastName and (phone or e-mail) exists
			// If exists update his phone or e-mail if one of them was changed
			int id = customerService.getIdIfExists(customer);

			if (id == 0) {
				customerService.saveCustomer(customer);
			} else {
				customer.setId(customerService.getIdIfExists(customer));
				customerService.saveCustomer(customer);
			}

			return "visit-confirmation";
		}

	}
	
	
	@GetMapping("/list")
	public String showVisitsList(@RequestParam("period") String per, Model model) {
		
		List<Visit> visits = new ArrayList<Visit>();
		
		if(per.equals("all")) {
			visits = visitService.getVisits();
		}else if(per.equals("today")) {
			visits = visitService.getVisitsPeriod(LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDateTime.now().toLocalDate().atStartOfDay().plusDays(1));
		}else if(per.equals("future")) {
			visits = visitService.getVisitsPeriod(LocalDateTime.now(), LocalDateTime.now().toLocalDate().atStartOfDay().plusYears(1));
		}else {visits = null;};
		
		model.addAttribute("visits", visits);
		
		return "visits-list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("visitId") int id, RedirectAttributes redirectAttributes) {
		
		visitService.deleteVisit(id);
		
		// Redirect "period" to /list and show all existing visits
		redirectAttributes.addAttribute("period", "all");
		
		return "redirect:/visit/list";
	}


	// Return all days with at least one free visit term (in time scope when booking is available)
	public List<LocalDate> getPossibleVisitDates(List<LocalDateTime> possibleTerms) {

		List<LocalDate> possibleDates = new ArrayList<>();

		for (LocalDateTime dateTime : possibleTerms) {
			if (!possibleDates.contains(dateTime.toLocalDate())) {
				possibleDates.add(dateTime.toLocalDate());
			}
		}

		return possibleDates;
	}

	
	// Return all free visit hours in chosen day
	public List<LocalTime> getFreeVisitHours(List<LocalDateTime> possibleVisitTerms, LocalDate visitDate) {

		List<LocalTime> possibleHours = new ArrayList<>();

		for (LocalDateTime date : possibleVisitTerms) {
			if (date.toLocalDate().equals(visitDate)) {
				possibleHours.add(date.toLocalTime());
			}
		}

		return possibleHours;
	}

}
