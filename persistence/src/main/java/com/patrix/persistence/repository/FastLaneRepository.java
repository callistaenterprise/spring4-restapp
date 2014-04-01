package com.patrix.persistence.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.domain.Page;
import java.io.Serializable;

@NoRepositoryBean
public interface FastLaneRepository<T, ID extends Serializable> extends Repository<T, ID> {
    /**
     * Returns a page of entities meeting the paging restriction provided in the pageable object.
     *
     * @param pageable the pageable.
     * @return a page of entities.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Returns all entities sorted by the given options
     *
     * @param sort sort directive.
     * @return all entities sorted by the given options
     */
    Iterable<T> findAll(Sort sort);


    /**
     * Retrieves an entity by its primary key.
     *
     * @param id the primary key.
     * @return the entity with the given primary key or null if none found.
     * @throws IllegalArgumentException - if primaryKey is null.
     */
    T findOne(ID id);
}
