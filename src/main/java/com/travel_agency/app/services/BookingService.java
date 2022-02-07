package com.travel_agency.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel_agency.app.models.Booking;
import com.travel_agency.app.repositories.BookingRepository;
import com.travel_agency.app.services.interfaces.BookingServiceInterface;

@Service
public class BookingService implements BookingServiceInterface{
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Override
	@Transactional
	public Booking save(Booking booking) {
		return this.bookingRepo.save(booking);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> getAll() {
		return this.bookingRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Booking getById(Long id) {
		Optional<Booking> booking=this.bookingRepo.findById(id);
		if(booking.isPresent()) {
			return booking.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> getByDni(String dni) {
		return this.bookingRepo.findByDni(dni).get();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.bookingRepo.deleteById(id);	
	}
}
