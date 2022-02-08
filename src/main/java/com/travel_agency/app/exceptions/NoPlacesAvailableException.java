package com.travel_agency.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NoPlacesAvailableException extends RuntimeException {

	public NoPlacesAvailableException(String message) {
		super(message);
	}

}
