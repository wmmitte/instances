package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Historique;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Historique.
 */
public interface HistoriqueService {

    /**
     * Save a historique.
     *
     * @param historique the entity to save
     * @return the persisted entity
     */
    Historique save(Historique historique);

    /**
     * Get all the historiques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Historique> findAll(Pageable pageable);


    /**
     * Get the "id" historique.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Historique> findOne(Long id);

    /**
     * Delete the "id" historique.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
