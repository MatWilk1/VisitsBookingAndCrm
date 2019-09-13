package mw.visitsbooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "visit")
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "customer_id")
	@NotNull
	@Valid
	private Customer customer;

	@Column(name = "date")
	@NotNull
	@Valid
	private LocalDateTime date;

	@Transient
	private LocalDate chosenDate;

	@Transient
	private LocalTime chosenTime;

	@Transient
	private boolean bookedDate;

	@Transient
	private List<LocalDate> dateOptions;

	@Transient
	private List<LocalTime> timeOptions;


	public Visit() {

		date = LocalDateTime.now();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDate getChosenDate() {
		return chosenDate;
	}

	public void setChosenDate(LocalDate chosenDate) {
		this.chosenDate = chosenDate;
	}

	public LocalTime getChosenTime() {
		return chosenTime;
	}

	public void setChosenTime(LocalTime chosenTime) {
		this.chosenTime = chosenTime;
	}

	public boolean isBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(boolean bookedDate) {
		this.bookedDate = bookedDate;
	}


	public List<LocalDate> getDateOptions() {
		return dateOptions;
	}

	public void setDateOptions(List<LocalDate> dateOptions) {
		this.dateOptions = dateOptions;
	}

	public List<LocalTime> getTimeOptions() {
		return timeOptions;
	}

	public void setTimeOptions(List<LocalTime> timeOptions) {
		this.timeOptions = timeOptions;
	}


	@Override
	public String toString() {
		return "Visit [id=" + id + ", customer=" + customer + ", date=" + date + "]";
	}


	// Init visits hours uses in drop-down list in form. One visit per hour.
	public List<LocalTime> initTimeOptions() {

		int visitFrequency = 60;

		List<LocalTime> init = new ArrayList<>();
		LocalTime startVisits = LocalTime.of(9, 0);

		for (int i = 0; i <= 450; i += visitFrequency) {
			init.add(startVisits.plusMinutes(i));
		}

		return init;
	}

	// Assemble visit date using chosen date and time. And set value for bookedDate marker. 
	public boolean createDate(List<LocalDateTime> possibleVisitTerms) {

		date = LocalDateTime.of(chosenDate, chosenTime);

		if (!possibleVisitTerms.contains(date)) {
			bookedDate = true;

			return true;
		} else {
			bookedDate = false;

			return false;
		}

	}


}
