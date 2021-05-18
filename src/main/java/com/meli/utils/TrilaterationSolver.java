package com.meli.utils;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.constantes.Constantes;
import com.meli.exceptions.ExceptionBusiness;
import com.meli.model.entity.SatelliteEntity;
import com.meli.repository.ISatellitesRepository;


@Component
public class TrilaterationSolver  {
  @Autowired ISatellitesRepository iSatellitesRepository;
  @Autowired Utilitarios utils;

    public Double[] getLocation(double... distances) throws ExceptionBusiness {
   	 //obtenemos la informacion de la ubicacion de los satelites en la db
    	 List<SatelliteEntity> satalites  = iSatellitesRepository.findByOrderByNameAsc() ;
    	 
        //obtenemos la posicion de los satelites 
        double[][] positions = getSatellitesPositions(satalites);
        
        //Enviamos la informacion de las posciiciones y distacias a la libreria la cual hace una trilateracion para obtener las posiciones del objetivo 
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        // obtenemos la respuesta
        double x  = optimum.getPoint().toArray()[0];
        double y  = optimum.getPoint().toArray()[1];
        
        // comenzamos a iterar la coleccion para validar uno por uno la ubicacion del punto con los radios de los satalites
        int i = 0;        
        for (SatelliteEntity satelite: satalites) {

            boolean validacion = Utilitarios.validateCorrectPosicion(
                    satelite.getX(),
                    satelite.getY(),
                    x,
                    y,
                    distances[i]);
            i++;
            //retornamos mensaje si la ubicacion no se encuentra en el radio
            if(!validacion)
                throw new ExceptionBusiness(Constantes.NOT_FIND_OUT_POSITION);
        }
        return new Double[]{x, y};
    }

    private double[][] getSatellitesPositions(List<SatelliteEntity> posiciones) {
    	// obtenemos  y separamos la posicion de los satelites registrados en la db 
        double[][]  positions = new double[posiciones.size()][];
        int i = 0;

        for (SatelliteEntity satelite: posiciones) {
            positions[i] = new double[]{satelite.getX(), satelite.getY()};
            i++;
        }
        return positions;
    }
}
