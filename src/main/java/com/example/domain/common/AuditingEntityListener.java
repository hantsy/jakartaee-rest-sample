/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.common;

import com.example.infrastructure.security.Authenticated;
import com.example.infrastructure.security.UserInfo;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hantsy
 */
public class AuditingEntityListener {

    private static final Logger LOG = Logger.getLogger(AuditingEntityListener.class.getName());

    @PrePersist
    public void beforePersist(Object entity) {
        if (entity instanceof AbstractAuditableEntity) {
            AbstractAuditableEntity o = (AbstractAuditableEntity) entity;
            final LocalDateTime now = LocalDateTime.now();
            o.setCreatedDate(now);
            o.setLastModifiedDate(now);
        }
    }

    @PreUpdate
    public void beforeUpdate(Object entity) {
        if (entity instanceof AbstractAuditableEntity) {
            AbstractAuditableEntity o = (AbstractAuditableEntity) entity;
            o.setLastModifiedDate(LocalDateTime.now());
        }
    }
}
