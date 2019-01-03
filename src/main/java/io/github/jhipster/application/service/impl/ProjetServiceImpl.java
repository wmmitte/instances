package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ProjetService;
import io.github.jhipster.application.domain.Projet;
import io.github.jhipster.application.repository.ProjetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Projet.
 */
@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

    private final Logger log = LoggerFactory.getLogger(ProjetServiceImpl.class);

    private final ProjetRepository projetRepository;

    public ProjetServiceImpl(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    /**
     * Save a projet.
     *
     * @param projet the entity to save
     * @return the persisted entity
     */
    @Override
    public Projet save(Projet projet) {
        log.debug("Request to save Projet : {}", projet);
        return projetRepository.save(projet);
    }

    /**
     * Get all the projets.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Projet> findAll() {
        log.debug("Request to get all Projets");
        return projetRepository.findAll();
    }


    /**
     * Get one projet by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Projet> findOne(Long id) {
        log.debug("Request to get Projet : {}", id);
        return projetRepository.findById(id);
    }

    /**
     * Delete the projet by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Projet : {}", id);
        projetRepository.deleteById(id);
    }
}
