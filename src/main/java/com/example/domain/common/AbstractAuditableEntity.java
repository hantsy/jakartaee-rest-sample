/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.common;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * @param <ID>
 * @author hantsy
 */
@MappedSuperclass
// applied it in orm.xml instead.
//@EntityListeners(AuditEntityListener.class)
public class AbstractAuditableEntity<ID> extends AbstractEntity<ID> {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedDate;

    @AttributeOverride(name = "username", column = @Column(name = "created_by"))
    private Username createdBy;

    @AttributeOverride(name = "username", column = @Column(name = "last_modified_by"))
    private Username lastModifiedBy;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Username getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Username createdBy) {
        this.createdBy = createdBy;
    }

    public Username getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Username lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
