package com.travel_agency.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel_agency.app.requests.FlightRequest;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {

	private static final long serialVersionUID = -3831551379088382507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 45, nullable = false)
	private String company;

	@Column(length = 10, nullable = false)
	private float price;

	@Column(length = 3, nullable = false)
	private int seatsAvailable;

	@Column(length = 45, nullable = false)
	private String origin;

	@Column(length = 45, nullable = false)
	private String destination;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Flight() {

	}

	public void setAllProperties(FlightRequest request) {
		this.setCompany(request.getCompany());
		this.setPrice(request.getPrice());
		this.setSeatsAvailable(request.getSeatsAvailable());
		this.setOrigin(request.getOrigin());
		this.setDestination(request.getDestination());
	}

	public Long getId() {
		return id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Float getPrice() {
		return (float) (Math.round(price * 100.0) / 100.0);
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Método que actualiza la propiedad updatedAt
	 */
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
}
