package com.meli.exceptions;

import org.springframework.http.HttpStatus;

// control de htttpStatus
public class ErrorServ {
	 public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private HttpStatus httpStatus;
	    private String message;

	    public ErrorServ(HttpStatus httpStatus, String message) {
	        this.httpStatus = httpStatus;
	        this.message = message;
	    }

}
