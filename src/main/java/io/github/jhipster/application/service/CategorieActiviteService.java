package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.CategorieActivite;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CategorieActivite.
 */
public interface CategorieActiviteService {

    /**
     * Save a categorieActivite.
     *
     * @param categorieActivite the entity to save
     * @return the persisted entity
     */
    CategorieActivite save(CategorieActivite categorieActivite);

    /**
     * Get all the categorieActivites.
     *
     * @return the list of entities
     */
    List<CategorieActivite> findAll();


    /**
     * Get the "id" categorieActivite.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CategorieActivite> findOne(Long id);

    /**
     * Delete the "id" categorieActivite.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
