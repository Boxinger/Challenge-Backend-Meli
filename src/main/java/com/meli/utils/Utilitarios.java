package com.meli.utils;


import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

import com.meli.constantes.Constantes;
import com.meli.exceptions.ExceptionBusiness;
import com.meli.model.entity.SatelliteEntity;
import com.meli.model.entity.SatelliteSplitEntity;
import com.meli.model.pojo.Satellite;
import com.meli.model.pojo.ServRequets;


@Component

public class Utilitarios {
	
	 public void validateExist(List<Satellite>  lista, List<SatelliteEntity> listSingleton) {
		 //validar si el tamaño de la list es igual al ingresado topsecret
	        if(lista.size() != listSingleton.size()){
	            throw new ExceptionBusiness(Constantes.NOT_QUANTITY);
	        }
	        lista.forEach(
	        		//compara los satelites ingresados contra los que estan en db topsercret
	                x-> { if(!listSingleton.stream().anyMatch(s-> x.getName().toLowerCase().equals(s.getName().toLowerCase())))
	                    throw new ExceptionBusiness(Constantes.NOT_EXIST_SATELLITES);
	                }
	        );
	    }
	 
	 public void validateExists(List<SatelliteSplitEntity>  lista, List<SatelliteEntity> listSingleton) {
		//validar si el tamaño de la list es igual al ingresado topsecretSplit
	        if(lista.size() != listSingleton.size()){
	            throw new ExceptionBusiness(Constantes.NOT_QUANTITY);
	        }
	        lista.forEach(
	        		//compara los satelites ingresados contra los que estan en db topsercretSplit
	                x-> { if(!listSingleton.stream().anyMatch(s-> x.getName().toLowerCase().equals(s.getName().toLowerCase())))
	                    throw new ExceptionBusiness(Constantes.NOT_EXIST_SATELLITES);
	                }
	        );
	    }
	 
	 
	 
	    public  void  order(ServRequets satelliteInfoRequest) {
	    	//ordernar la informacion entregada por el requets
	        List<Satellite> list = satelliteInfoRequest.getSatellites();
	        Collections.sort(list, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
	        satelliteInfoRequest.setSatellites(list);
	    }
	    
	    
	    public double[] getDistances(List<Satellite> list) {
	    //separa la informacion de la list para solo obtener las distancias topsecret
	        double[] distances = new double[list.size()];
	        int i = 0;
	        for (Satellite satelite : list) {
	            distances[i] = new Double(satelite.getDistance());
	            i++;
	        }
	        return distances;
	    }
	    
	    public String getMessage(List<Satellite> list) {
	    	// separa la informacion de la list para obtener el mensajes de topsecret
	        String[][] messages = list.stream()
	                .map(Satellite::getMessage)
	                .toArray(String[][]::new);
	        
	        //el mensaje enviado se ordena para obtener un string
	        String message = MessageSolver.getMessage(messages);
	        return message;
	    }

	    public static boolean validateCorrectPosicion(double x1, double y1, double x2, double y2, double radio) {
	    	
	    	//se valida la distancia del objetivo con el radio del satelite
	         double distance = Math.sqrt(
	                Math.pow((x2 - x1), 2) +
	                Math.pow((y2 - y1), 2)
	        );
	         //si el radio es mayor no se encuetra la posiscion 
	         if((radio+30) < distance)
	            return  false ;
	         return  true;
	    }
	    
	    public double[] getDistancess(List<SatelliteSplitEntity> list) {
	    	//separa la informacion de la list para solo obtener las distancias topsecretSplit
	        double[] distances = new double[list.size()];
	        int i = 0;
	        for (SatelliteSplitEntity satelite : list) {
	            distances[i] = new Double(satelite.getDistancia());
	            i++;
	        }
	        return distances;
	    }
	    
	    public String getMessages(List<SatelliteSplitEntity> list) {
	    	// separa la informacion de la list para obtener el mensajes de topsecretSplit
	        String[][] messages = list.stream()
	                .map(SatelliteSplitEntity::getMessage)
	                .toArray(String[][]::new);
	      //el mensaje enviado se ordena para obtener un string TopsercretSplit
	        String message = MessageSolver.getMessage(messages);
	        return message;
	    }
	    


}
