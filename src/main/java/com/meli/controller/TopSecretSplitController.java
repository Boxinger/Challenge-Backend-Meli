package com.meli.controller;


import com.meli.config.Tags;
import com.meli.model.pojo.ServResponse;
import com.meli.model.pojo.ServSplitRequest;
import com.meli.service.TopSecretSplitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import javax.validation.Valid;

@RestController
@RequestMapping(value =  TopSecretSplitController.BASE_PATH)
@Api(tags = {Tags.TOPSECRETSPLIT_CONTROLLER} )
public class TopSecretSplitController {

    static final String BASE_PATH =  "/topsecret_split";

   
    @Autowired TopSecretSplitService topSecretSplitService;
    
    
    //servicio Post para la operacion TopSecretSplit
    @PostMapping("/{name}")
    @ApiOperation(value = "enviar informacion del mensaje aun satellite a la vez")
    public ResponseEntity<Object> saveMessage (@PathVariable String name, @Valid  @RequestBody ServSplitRequest requets) throws ParseException {
        try {
        	//obtenemos el nombre y lo agregamos a nuestro objeto Requets
        	requets.setName(name.toLowerCase());
        	
        	//llamamos el service de topsecretSplit para guardar la informacion
          topSecretSplitService.saveSatelliteinfo(requets);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            throw  ex;
        }
    }

    
    //servicio Post para la operacion TopSecretSplit
    @GetMapping()
    @ApiOperation(value = "determianr la posicion y el mensaje de los saatellites")
    public ResponseEntity<ServResponse> trilaterationResponse (){
        try {
        	//llamamos el service de topsecretSplit para obtener la informacion
        	ServResponse response = topSecretSplitService.getInfo();
        	
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw  ex;
        }
    }
}
