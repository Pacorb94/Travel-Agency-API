package com.travel_agency.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "bookings")
public class Booking implements Serializable {
	
	private static final long serialVersionUID = -1114678244992477905L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 9, nullable = false)
	private String dni;
	
	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Booking() {
		
	}

	public long getId() {
		return id;
	}

	public Flight getFlight() {
		return flight;
	}

	public Hotel getHotel() {
		return hotel;
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
