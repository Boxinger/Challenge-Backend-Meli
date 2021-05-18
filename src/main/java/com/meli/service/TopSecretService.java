package com.meli.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.exceptions.ExceptionBusiness;
import com.meli.model.pojo.Posicion;
import com.meli.model.pojo.ServRequets;
import com.meli.model.pojo.ServResponse;
import com.meli.repository.ISatellitesRepository;
import com.meli.utils.TrilaterationSolver;
import com.meli.utils.Utilitarios;

@Service
public class TopSecretService  {


 
    

    @Autowired TrilaterationSolver trilaterationSolver;
    @Autowired ISatellitesRepository iSatellitesRepository;
    @Autowired Utilitarios utils;
   
  

  
    public ServResponse getTrilateration(ServRequets satelliteInfoRequest) throws ExceptionBusiness {
    	//Valida Exitencia de los saltelites enviados con los configurados en la db
    	utils.validateExist(satelliteInfoRequest.getSatellites(),iSatellitesRepository.findAll());
    	
        //orden los satelites enviado en el request
       	utils.order(satelliteInfoRequest);
       	
       	//obtiene las distancias ingresadas
        double[] distances = utils.getDistances(satelliteInfoRequest.getSatellites());
        
        //obtiene las cordenada  realizando la trialeteracion y valida la distancian de los radio con el objetivo         
        Double[] cords = trilaterationSolver.getLocation(distances);        
        Posicion position = new Posicion(cords[0], cords[1]);
        
        //obtine el mesaje cifrado segun los los enviados en el request
        String message = utils.getMessage(satelliteInfoRequest.getSatellites());
        
        //retorna response
        return new ServResponse(position, message);
    }



}
