package com.meli.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.constantes.Constantes;
import com.meli.exceptions.ExceptionBusiness;
import com.meli.model.entity.SatelliteSplitEntity;
import com.meli.model.pojo.Posicion;
import com.meli.model.pojo.ServResponse;
import com.meli.model.pojo.ServSplitRequest;
import com.meli.repository.ISatellitesInfoRepository;
import com.meli.repository.ISatellitesRepository;
import com.meli.utils.TrilaterationSolver;
import com.meli.utils.Utilitarios;


@Service
public class TopSecretSplitService {
	
	
	  @Autowired TrilaterationSolver trilaterationSolver;
	    @Autowired ISatellitesInfoRepository iSatellitesInfoRepository;
	    @Autowired ISatellitesRepository iSatellitesRepository;
	    @Autowired Utilitarios utils;
	    
	    
	   //metodo Post guarda la informacion en la db
	    public void saveSatelliteinfo(ServSplitRequest request)throws ExceptionBusiness {
	    	
	    	SatelliteSplitEntity info = new SatelliteSplitEntity();
	    	
	    	//Seteamos la informacion del request a nuestro objeto de la entity
	    	info.setName(request.getName());
	    	info.setDistancia(request.getDistance());
	    	info.setMessage(request.getMessage());
	    	
	    	//validamos si el satalite enviado se encuentra en la db
	    	boolean flag = iSatellitesRepository.existsById(info.getName());
	    	if (!flag) {
	    		 throw new ExceptionBusiness(Constantes.NOT_EXIST);
			}
	    	
	    	// se guarda la informacion en la db
	    	iSatellitesInfoRepository.save(info);
	    	
	    	
			
		}
	    
	    
	    // metodo Get Servicio topsecretSplit
    public ServResponse getInfo() throws ExceptionBusiness {
    	
    	//obtenemos la informacion guardada en la db del servicio post topsecretSplit
    	List<SatelliteSplitEntity> Satelites = iSatellitesInfoRepository.findByOrderByNameAsc();
    	
    	 //validamos que los satelites enviados sean iguales a los parametrizados 	
    	utils.validateExists(Satelites,iSatellitesRepository.findAll());
    	
    	//obtiene las distancias ingresadas
         double[] distances = utils.getDistancess(Satelites);
         
       //obtiene las cordenada  realizando la trialeteracion y valida la distancian de los radio con el objetivo
         Double[] cords = trilaterationSolver.getLocation(distances);
         Posicion position = new Posicion(cords[0], cords[1]);
         
       //obtine el mesaje cifrado segun los los enviados en el request
         String message = utils.getMessages(Satelites);
         
       //retorna response
         return new ServResponse(position, message );
    	
    	
    }


}
