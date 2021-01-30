package com.example.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String caller);
    
    Optional<User> findByEmail(String email);
    
    User save(User user);
    
    List<User> findAll();
    
    long countAll();
    
    boolean delete(User user);
}
