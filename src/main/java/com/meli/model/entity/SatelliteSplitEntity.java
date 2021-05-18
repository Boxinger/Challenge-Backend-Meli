package com.meli.model.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.meli.utils.StringListConverter;

//entity de la distancia y mensajes de los satelites

@Entity
@Table(
	    name = "satellitesInfo"
	)

public class SatelliteSplitEntity   {
	
	
	@Id
	private String name;
	
	@Column(nullable = true)
	private String distancia;
	

	@Column(length = 5000)
    @Convert(converter = StringListConverter.class)
    private String[] message;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	
	
	
	
	
	





	
	
}
