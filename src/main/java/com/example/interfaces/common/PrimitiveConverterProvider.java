/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.interfaces.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PrimitiveConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (rawType.getName().equals(boolean.class.getName())) {

            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    return (T) Boolean.valueOf(value != null && value.equals("on"));
                }

                @Override
                public String toString(T value) {
                    return ((Boolean) value) ? "on" : "";
                }
            };

        } else if (rawType.getName().equals(int.class.getName())) {

            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {

                    try {
                        return (T) (Integer) Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                    }

                    return (T) (Integer) 0;
                }

                @Override
                public String toString(T value) {
                    return "" + value;
                }
            };

        } else {
            return null;
        }
    }
}
