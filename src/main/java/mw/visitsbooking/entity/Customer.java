package mw.visitsbooking.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	@NotNull(message = "is required")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "is required")
	private String lastName;

	@Column(name = "phone_number")
	@NotNull(message = "is required")
	@Pattern(regexp = "^[^a-zA-Z]+", message = "phone number should be valid")
	private String phoneNumber;

	@Column(name = "email")
	@NotNull(message = "is required")
	@Email(message = "e-mail should be valid")
	private String email;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Visit> visits;
	
	// Formula to retrieve number of visits. For bigger bases should use different approach to avoid efficiency problem.
	@Formula("(select count(v.id) from visit v where v.customer_id = id)")
	private int visitsNumber;

	public Customer() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	
	public int getVisitsNumber() {
		return visitsNumber;
	}

	public void setVisitsNumber(int visitsNumber) {
		this.visitsNumber = visitsNumber;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + "]";
	}
	
}
