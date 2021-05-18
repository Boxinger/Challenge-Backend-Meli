package com.meli.model.pojo;

// objeto response de  los servicios
public class ServResponse {
	 public ServResponse(Posicion posicion, String message) {
	        this.posicion = posicion;
	        this.message = message;
	    }

	    public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

		private Posicion posicion;
	    private String message;



}
