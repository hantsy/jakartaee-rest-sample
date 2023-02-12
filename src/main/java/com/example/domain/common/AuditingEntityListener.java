/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.common;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hantsy
 */
public class AuditingEntityListener {

    private static final Logger LOG = Logger.getLogger(AuditingEntityListener.class.getName());

    @PrePersist
    public void beforePersist(Object entity) {
        LOG.log(Level.INFO, "before persist: {0}", new Object[]{entity});
        if (entity instanceof AbstractAuditableEntity<?> o) {
            final LocalDateTime now = LocalDateTime.now();
            o.setCreatedDate(now);
            o.setLastModifiedDate(now);
        }
    }

    @PreUpdate
    public void beforeUpdate(Object entity) {
        LOG.log(Level.INFO, "before update: {0}", new Object[]{entity});
        if (entity instanceof AbstractAuditableEntity<?> o) {
            o.setLastModifiedDate(LocalDateTime.now());
        }
    }
}
