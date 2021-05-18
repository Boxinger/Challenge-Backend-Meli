package com.meli.model.pojo;

// Objeto Satellite
public class Satellite {
	private String name;
	private Double distance;
	private String[] message;

	
	
	public Satellite() {
	}

	public Satellite(String name, Double distance, String[] message) {
		super();
		this.name = name;
		this.distance = distance;
		
		this.message = message;
	}

	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

}
