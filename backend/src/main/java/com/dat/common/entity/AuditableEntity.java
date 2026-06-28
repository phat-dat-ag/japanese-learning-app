package com.dat.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditableEntity extends CreatedEntity {

    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt;

    @Override
    @PrePersist
    protected void onCreate() {
        super.onCreate();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
