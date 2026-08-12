package com.example.tutorial.repository;

import com.example.tutorial.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    /*
     * =========================
     * FIND BY ID
     * =========================
     */

    @Override
    Optional<T> findById(ID id);

    Optional<T> findById(ID id, boolean includeDeleted);


    /*
     * =========================
     * FIND ALL
     * =========================
     */

    @Override
    List<T> findAll();

    List<T> findAll(boolean includeDeleted);


    /*
     * =========================
     * PAGINATION
     * =========================
     */

    @Override
    Page<T> findAll(Pageable pageable);

    Page<T> findAll(
            Pageable pageable,
            boolean includeDeleted
    );


    /*
     * =========================
     * EXISTS
     * =========================
     */

    @Override
    boolean existsById(ID id);

    boolean existsById(
            ID id,
            boolean includeDeleted
    );


    /*
     * =========================
     * SOFT DELETE
     * =========================
     */

    boolean softDeleteById(ID id);

    boolean softDeleteById(
            ID id,
            String reason
    );
}