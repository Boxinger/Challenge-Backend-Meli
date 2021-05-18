package com.meli.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.meli.config.Tags;
import com.meli.exceptions.ExceptionBusiness;
import com.meli.model.pojo.ServRequets;
import com.meli.model.pojo.ServResponse;
import com.meli.service.TopSecretService;


import io.swagger.annotations.Api;


@RestController
@RequestMapping(value =  TopSecretController.BASE_PATH)
@Api(tags = {Tags.TOPSECRET_CONTROLLER} )


public class TopSecretController {

    public static final String BASE_PATH =  "/topsecret";

    @Autowired TopSecretService topSecretService;
    @PostMapping
    @ResponseBody
    public ResponseEntity<ServResponse> ObtenerInformacion (@Valid  @RequestBody ServRequets requets)  {
        try {
        	
        	//ejecuta  service para obtneer mesagge y location 
        	ServResponse response = topSecretService.getTrilateration(requets);
              return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ExceptionBusiness ex){
            throw  ex;
        }
    }

}
