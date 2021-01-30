/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.util.hash;



import com.example.application.util.hash.bcrypt.BCryptPasswordEncoder;
import com.example.application.util.hash.plain.PlainPasswordEncoder;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author hantsy
 */
@Dependent
public class PasswordEncoderProducer {

    @Produces
    @Crypto
    public PasswordEncoder passwordEncoder(InjectionPoint ip) {
        Crypto crypto = ip.getAnnotated().getAnnotation(Crypto.class);
        Crypto.Type type = crypto.value();
        PasswordEncoder encoder;
        switch (type) {
            case BCRYPT:
                encoder = new BCryptPasswordEncoder();
                break;
            default:
                encoder = new PlainPasswordEncoder();
                break;
        }

        return encoder;
    }

}
