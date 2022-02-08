package com.travel_agency.app.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase que maneja las excepciones
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	public CustomExceptionHandler() {
		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleServerExceptions(Exception ex) {
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Server Error", details);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<Object> handleHotelNotFoundException(
			HotelNotFoundException ex
	) {
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Hotel not found", details);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFoundException(
			FlightNotFoundException ex
	) {
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Flight not found", details);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Object> handleBookingNotFoundException(
			BookingNotFoundException ex
	) {
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Booking not found", details);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NameAlreadyExistsException.class)
	public ResponseEntity<Object> handleNameAlreadyExistsException(
			NameAlreadyExistsException ex
	){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Name already exists", details);
		return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoSeatsAvailableException.class)
	public ResponseEntity<Object> handleNoSeatsAvailableException(
			NoSeatsAvailableException ex
	){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("No seats available for this flight", details);
		return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoPlacesAvailableException.class)
	public ResponseEntity<Object> handleNoPlacesAvailableException(
			NoPlacesAvailableException ex
	){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("No places available for this hotel", details);
		return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request
	) {
		List<String> details = new ArrayList<>();
		//Añadimos los mensajes de error
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
