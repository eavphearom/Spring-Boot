package com.example.tutorial.entity;

import com.example.tutorial.util.SecurityUtil;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "create_uid")
    private Long createUid;

    @Column(name = "update_uid")
    private Long updateUid;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_reason")
    private String deletedReason;

    @Column(name = "deleted_uid")
    private Long deletedUid;

    @PrePersist
    protected void onCreate() {
        Long userId = SecurityUtil.getCurrentUserId();

        this.createUid = userId;
        this.updateUid = userId;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateUid = SecurityUtil.getCurrentUserId();
    }
}