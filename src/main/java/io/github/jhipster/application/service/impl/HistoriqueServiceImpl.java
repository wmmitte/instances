package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.HistoriqueService;
import io.github.jhipster.application.domain.Historique;
import io.github.jhipster.application.repository.HistoriqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Historique.
 */
@Service
@Transactional
public class HistoriqueServiceImpl implements HistoriqueService {

    private final Logger log = LoggerFactory.getLogger(HistoriqueServiceImpl.class);

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueServiceImpl(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    /**
     * Save a historique.
     *
     * @param historique the entity to save
     * @return the persisted entity
     */
    @Override
    public Historique save(Historique historique) {
        log.debug("Request to save Historique : {}", historique);
        return historiqueRepository.save(historique);
    }

    /**
     * Get all the historiques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Historique> findAll(Pageable pageable) {
        log.debug("Request to get all Historiques");
        return historiqueRepository.findAll(pageable);
    }


    /**
     * Get one historique by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Historique> findOne(Long id) {
        log.debug("Request to get Historique : {}", id);
        return historiqueRepository.findById(id);
    }

    /**
     * Delete the historique by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Historique : {}", id);
        historiqueRepository.deleteById(id);
    }
}
