package com.example.application.util.hash.plain;


import com.example.application.util.hash.PasswordEncoder;

/**
 *
 * @author hantsy
 */
public class PlainPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

}
