package com.travel_agency.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travel_agency.app.models.Flight;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

	@Transactional(readOnly = true)
	@Query("select f from flights f where f.seats_available >= ?1")
	public Optional<List<Flight>> findBySeatsAvailable(int seatsAvailable);
	
	@Transactional(readOnly = true)
	@Query("select f from flights f where f.price <= ?1")
	public Optional<List<Flight>> findByPrice(float price);
}
