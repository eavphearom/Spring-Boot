package com.example.tutorial.repository.impl;

import com.example.tutorial.entity.BaseEntity;
import com.example.tutorial.repository.BaseRepository;
import com.example.tutorial.util.SecurityUtil;

import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<
        T extends BaseEntity,
        ID extends Serializable
        >
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    protected final EntityManager entityManager;


    /*
     * =========================
     * CONSTRUCTOR
     * =========================
     */

    public BaseRepositoryImpl(
            JpaEntityInformation<T, ID> entityInformation,
            EntityManager entityManager
    ) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    /*
     * =========================
     * FIND BY ID
     * =========================
     */

    @Override
    public Optional<T> findById(ID id) {
        return findById(id, false);
    }

    @Override
    public Optional<T> findById(
            ID id,
            boolean includeDeleted
    ) {

        Optional<T> entity = super.findById(id);

        if (includeDeleted) {
            return entity;
        }

        return entity.filter(
                item -> !Boolean.TRUE.equals(
                        item.getIsDeleted()
                )
        );
    }


    /*
     * =========================
     * FIND ALL
     * =========================
     */

    @Override
    public List<T> findAll() {
        return findAll(false);
    }

    @Override
    public List<T> findAll(boolean includeDeleted) {

        if (includeDeleted) {
            return super.findAll();
        }

        return super.findAll(notDeletedSpec());
    }


    /*
     * =========================
     * PAGINATION
     * =========================
     */

    @Override
    public Page<T> findAll(Pageable pageable) {
        return findAll(pageable, false);
    }

    @Override
    public Page<T> findAll(
            Pageable pageable,
            boolean includeDeleted
    ) {

        if (includeDeleted) {
            return super.findAll(pageable);
        }

        return super.findAll(
                notDeletedSpec(),
                pageable
        );
    }


    /*
     * =========================
     * EXISTS
     * =========================
     */

    @Override
    public boolean existsById(ID id) {
        return existsById(id, false);
    }

    @Override
    public boolean existsById(
            ID id,
            boolean includeDeleted
    ) {

        Optional<T> entity = super.findById(id);

        if (entity.isEmpty()) {
            return false;
        }

        if (includeDeleted) {
            return true;
        }

        return !Boolean.TRUE.equals(
                entity.get().getIsDeleted()
        );
    }


    /*
     * =========================
     * SOFT DELETE
     * =========================
     */

    @Override
    @Transactional
    public boolean softDeleteById(ID id) {
        return softDeleteById(id, null);
    }

    @Override
    @Transactional
    public boolean softDeleteById(
            ID id,
            String reason
    ) {

        // Default findById() already excludes deleted records
        T entity = findById(id).orElse(null);

        if (entity == null) {
            return false;
        }

        entity.setIsDeleted(true);
        entity.setDeletedAt(LocalDateTime.now());
        entity.setDeletedReason(reason);
        entity.setDeletedUid(
                SecurityUtil.getCurrentUserId()
        );

        save(entity);

        return true;
    }


    /*
     * =========================
     * SPECIFICATIONS
     * =========================
     */

    private Specification<T> notDeletedSpec() {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isFalse(
                        root.get("isDeleted")
                );
    }
}