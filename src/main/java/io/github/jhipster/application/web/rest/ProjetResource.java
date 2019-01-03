package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Projet;
import io.github.jhipster.application.service.ProjetService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Projet.
 */
@RestController
@RequestMapping("/api")
public class ProjetResource {

    private final Logger log = LoggerFactory.getLogger(ProjetResource.class);

    private static final String ENTITY_NAME = "projet";

    private final ProjetService projetService;

    public ProjetResource(ProjetService projetService) {
        this.projetService = projetService;
    }

    /**
     * POST  /projets : Create a new projet.
     *
     * @param projet the projet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projet, or with status 400 (Bad Request) if the projet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projets")
    @Timed
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) throws URISyntaxException {
        log.debug("REST request to save Projet : {}", projet);
        if (projet.getId() != null) {
            throw new BadRequestAlertException("A new projet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Projet result = projetService.save(projet);
        return ResponseEntity.created(new URI("/api/projets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projets : Updates an existing projet.
     *
     * @param projet the projet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projet,
     * or with status 400 (Bad Request) if the projet is not valid,
     * or with status 500 (Internal Server Error) if the projet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projets")
    @Timed
    public ResponseEntity<Projet> updateProjet(@RequestBody Projet projet) throws URISyntaxException {
        log.debug("REST request to update Projet : {}", projet);
        if (projet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Projet result = projetService.save(projet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projets : get all the projets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projets in body
     */
    @GetMapping("/projets")
    @Timed
    public List<Projet> getAllProjets() {
        log.debug("REST request to get all Projets");
        return projetService.findAll();
    }

    /**
     * GET  /projets/:id : get the "id" projet.
     *
     * @param id the id of the projet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projet, or with status 404 (Not Found)
     */
    @GetMapping("/projets/{id}")
    @Timed
    public ResponseEntity<Projet> getProjet(@PathVariable Long id) {
        log.debug("REST request to get Projet : {}", id);
        Optional<Projet> projet = projetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projet);
    }

    /**
     * DELETE  /projets/:id : delete the "id" projet.
     *
     * @param id the id of the projet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projets/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        log.debug("REST request to delete Projet : {}", id);
        projetService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
