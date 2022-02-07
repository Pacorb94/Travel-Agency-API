package com.travel_agency.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travel_agency.app.models.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	// Al usar findBy... ya está configurado para buscar
	@Transactional(readOnly = true)
	public Optional<List<Hotel>> findByCity(String city);

	@Transactional(readOnly = true)
	@Query("select h from hotels h where h.places_available >= ?1")
	public Optional<List<Hotel>> findByPlacesAvailable(int placesAvailable);

	@Transactional(readOnly = true)
	public Optional<List<Hotel>> findByCategory(int category);
}
