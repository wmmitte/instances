package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.CategorieActiviteService;
import io.github.jhipster.application.domain.CategorieActivite;
import io.github.jhipster.application.repository.CategorieActiviteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing CategorieActivite.
 */
@Service
@Transactional
public class CategorieActiviteServiceImpl implements CategorieActiviteService {

    private final Logger log = LoggerFactory.getLogger(CategorieActiviteServiceImpl.class);

    private final CategorieActiviteRepository categorieActiviteRepository;

    public CategorieActiviteServiceImpl(CategorieActiviteRepository categorieActiviteRepository) {
        this.categorieActiviteRepository = categorieActiviteRepository;
    }

    /**
     * Save a categorieActivite.
     *
     * @param categorieActivite the entity to save
     * @return the persisted entity
     */
    @Override
    public CategorieActivite save(CategorieActivite categorieActivite) {
        log.debug("Request to save CategorieActivite : {}", categorieActivite);
        return categorieActiviteRepository.save(categorieActivite);
    }

    /**
     * Get all the categorieActivites.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategorieActivite> findAll() {
        log.debug("Request to get all CategorieActivites");
        return categorieActiviteRepository.findAll();
    }


    /**
     * Get one categorieActivite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieActivite> findOne(Long id) {
        log.debug("Request to get CategorieActivite : {}", id);
        return categorieActiviteRepository.findById(id);
    }

    /**
     * Delete the categorieActivite by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieActivite : {}", id);
        categorieActiviteRepository.deleteById(id);
    }
}
