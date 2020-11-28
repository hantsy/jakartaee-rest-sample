package com.example.security.hash;

/**
 * @author hantsy
 */
public interface PasswordEncoder {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

}
