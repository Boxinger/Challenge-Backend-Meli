package com.meli.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.meli.model.entity.SatelliteEntity;


//repository Crud de las posiciones de los satelites  topsecret
public interface ISatellitesRepository extends CrudRepository<SatelliteEntity, String> {

	public List<SatelliteEntity> findByOrderByNameAsc();
	public List<SatelliteEntity> findAll();
	

}
