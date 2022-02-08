package com.travel_agency.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel_agency.app.requests.BookingRequest;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable {
	
	private static final long serialVersionUID = -1114678244992477905L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 9, nullable = false)
	private String dni;
	
	@Column(nullable = false)
	private int clientsNumber;
	
	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hotel hotel;
	
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Booking() {
		
	}
	
	public void setAllProperties(
		BookingRequest request,
		Flight flight,
		Hotel hotel
	) {
		this.setDni(request.getDni());
		this.setClientsNumber(request.getClientsNumber());
		this.setFlight(flight);
		this.setHotel(hotel);
	}

	public long getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getClientsNumber() {
		return clientsNumber;
	}

	public void setClientsNumber(int clientsNumber) {
		this.clientsNumber = clientsNumber;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Hotel getHotel() {
		return hotel;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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
