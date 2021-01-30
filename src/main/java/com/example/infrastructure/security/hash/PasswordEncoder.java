package com.example.infrastructure.security.hash;

/**
 * @author hantsy
 */
public interface PasswordEncoder {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

}
