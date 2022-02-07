package com.travel_agency.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel_agency.app.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	public Optional<List<Booking>> findByDni(String dni);
}
