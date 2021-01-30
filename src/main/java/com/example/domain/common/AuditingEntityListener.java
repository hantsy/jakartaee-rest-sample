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

            if (o.getCreatedBy() == null) {
                o.setCreatedBy(currentUser());
            }
        }
    }

    @PreUpdate
    public void beforeUpdate(Object entity) {
        if (entity instanceof AbstractAuditableEntity) {
            AbstractAuditableEntity o = (AbstractAuditableEntity) entity;
            o.setLastModifiedDate(LocalDateTime.now());

            if (o.getLastModifiedBy() == null) {
                o.setLastModifiedBy(currentUser());
            }
        }
    }

    private Username currentUser() {
        try {
            UserInfo user = CDI.current().select(UserInfo.class, Authenticated.INSTANCE).get();
            LOG.log(Level.INFO, "set username for entity in AuditEntityListener: {0}", user);
            if (user == null) {
                return null;
            }
            return new Username(user.getName());
        } catch (Exception e) {
            return null;
        }

    }
}
