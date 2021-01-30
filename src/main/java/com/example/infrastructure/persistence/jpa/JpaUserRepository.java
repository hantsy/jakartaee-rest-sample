package com.example.infrastructure.persistence.jpa;


import com.example.domain.user.User;
import com.example.domain.user.UserRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Stateless
public class JpaUserRepository extends AbstractRepository<User, Long> implements UserRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager entityManager() {
        return this.em;
    }
    
    @Override
    public Optional<User> findByUsername(String caller) {
        Objects.requireNonNull(caller, "username can not be null");
        return this.stream().filter(u -> u.getUsername().equals(caller)).findFirst();
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        Objects.requireNonNull(email, "email can not be null");
        return this.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }
    
    @Override
    public long countAll() {
        return this.stream().count();
    }
    
}
