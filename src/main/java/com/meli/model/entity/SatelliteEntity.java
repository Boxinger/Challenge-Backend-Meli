package com.meli.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(
	    name = "satellites"
	)

public class SatelliteEntity  {
	
	
    @Id
	@Column
	private String name;
	
	@Column
	private double x;
	
	@Column
	private double y;
	
	@Column(nullable = true)
	private Double distancia;

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}


	
	
}
