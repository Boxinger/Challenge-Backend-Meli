package com.meli.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<String[], String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    // convertmos un array a un string
    public  String convertToDatabaseColumn(String[] stringAsArray) {
        return String.join(SPLIT_CHAR , stringAsArray).concat(" ");
    }

    @Override
    // convertimos un String a un Array
    public String[] convertToEntityAttribute(String string) {
        return string.split(SPLIT_CHAR);
    }
}