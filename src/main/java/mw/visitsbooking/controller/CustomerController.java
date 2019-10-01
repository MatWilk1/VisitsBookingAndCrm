package mw.visitsbooking.controller;

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
@RequestMapping("/customer")
public class CustomerController {

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

	@GetMapping("/list")
	public String showCustomersList(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);

		return "customers-list";
	}

	@GetMapping("/formForUpdate")
	public String updateCustomer(@RequestParam("customerId") int id, Model model) {

		Customer customer = customerService.getCustomer(id);

		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
		
		
		if(bindingResult.hasErrors()) {
			return "customer-form";
		}
		else {
			customerService.saveCustomer(customer);
			
			return "redirect:/customer/list";
		}
		
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {

		customerService.deleteCustomer(id);

		return "redirect:/customer/list";
	}

	@GetMapping("/deleteVisit")
	public String deleteVisit(@RequestParam("visitId") int id, RedirectAttributes redirectAttributes) {

		Customer cust = visitService.getVisit(id).getCustomer();
		int custId = cust.getId();

		visitService.deleteVisit(id);

		// Redirect customer id to /customerVisitsList to show all existing visits
		redirectAttributes.addAttribute("customerId", custId);

		return "redirect:/customer/customerVisitsList";
	}

	@GetMapping("/customerVisitsList")
	public String customerVisits(@RequestParam("customerId") int id, Model model) {

		Customer customer = customerService.getCustomer(id);

		List<Visit> visits = visitService.searchVisits(customer);

		model.addAttribute("visits", visits);
		model.addAttribute("customer", customer);

		return "customer-visits-list";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("name") String name, Model model) {

		List<Customer> customers = customerService.searchCustomers(name);

		model.addAttribute("customers", customers);

		return "customers-list";
	}
}
