package mw.visitsbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mw.visitsbooking.entity.Customer;
import mw.visitsbooking.entity.Visit;
import mw.visitsbooking.service.CustomerService;
import mw.visitsbooking.service.VisitService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private VisitService visitService;
	
	@GetMapping("/list")
	public String showCustomersList(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers", customers);
		
		return "customers-list";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/deleteVisit")
	public String deleteVisit(@RequestParam("visitId") int id, RedirectAttributes redirectAttributes) {
		
		Customer cust = visitService.getVisit(id).getCustomer();
		int custId =  cust.getId();
		visitService.deleteVisit(id);

		// Redirect customer id to /customerVisitsList to show all existing visits
		redirectAttributes.addAttribute("customerId", custId);
		
		return "redirect:/customer/customersVisits";
	}
	
	@GetMapping("/customerVisitsList")
	public String customerVisits(@RequestParam("customerId") int id, Model model) {
		
		List<Visit> visits = visitService.searchVisits(customerService.getCustomer(id));
		
		model.addAttribute("visits", visits);
		
		return "customer-visits-list";
	}
}
