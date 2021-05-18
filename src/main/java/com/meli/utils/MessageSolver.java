package com.meli.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.meli.constantes.Constantes;
import com.meli.exceptions.ExceptionBusiness;

public class MessageSolver  {


    public static  String getMessage(String[][] messages) {
    	
        int wordsCount = messages[0].length;
        String []  message = new String[wordsCount];


        if(Arrays.stream(messages).filter(x-> Arrays.stream(x).count() !=  wordsCount).count()> 0 )
            throw  new ExceptionBusiness(Constantes.NOT_FIND_OUT_MESSAGE);
        int i = 0;
        for (String[] words : messages) {
            i = 0;
            for (String word : words) {
                if(!Objects.isNull(message[i]) && !word.trim().equals("") && !word.trim().equals(message[i].trim())){
                    throw  new ExceptionBusiness(Constantes.NOT_FIND_OUT_MESSAGE);
                }else if(!word.trim().equals(""))
                {
                    message[i] = word.trim();
                }
                i++;
            }
        }
        return Arrays.asList(message)
                .stream()
                .collect(Collectors.joining(" "));
    }
}