package com.meli.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.meli.model.entity.SatelliteSplitEntity;

// respository Crud de la distancia de los requets topsecretSplit
public interface ISatellitesInfoRepository extends CrudRepository<SatelliteSplitEntity, String> {

	public List<SatelliteSplitEntity> findByOrderByNameAsc();	
	public  List<SatelliteSplitEntity> findAll();
	

}
