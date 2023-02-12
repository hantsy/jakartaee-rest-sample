package com.example.domain.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
