package com.example.common.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class Boolean2StringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "on" : "off";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "on".equals(dbData) || Boolean.parseBoolean(dbData) || Boolean.FALSE;
    }
}
