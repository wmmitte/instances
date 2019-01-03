package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.CategorieActivite;
import io.github.jhipster.application.service.CategorieActiviteService;
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
 * REST controller for managing CategorieActivite.
 */
@RestController
@RequestMapping("/api")
public class CategorieActiviteResource {

    private final Logger log = LoggerFactory.getLogger(CategorieActiviteResource.class);

    private static final String ENTITY_NAME = "categorieActivite";

    private final CategorieActiviteService categorieActiviteService;

    public CategorieActiviteResource(CategorieActiviteService categorieActiviteService) {
        this.categorieActiviteService = categorieActiviteService;
    }

    /**
     * POST  /categorie-activites : Create a new categorieActivite.
     *
     * @param categorieActivite the categorieActivite to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categorieActivite, or with status 400 (Bad Request) if the categorieActivite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/categorie-activites")
    @Timed
    public ResponseEntity<CategorieActivite> createCategorieActivite(@RequestBody CategorieActivite categorieActivite) throws URISyntaxException {
        log.debug("REST request to save CategorieActivite : {}", categorieActivite);
        if (categorieActivite.getId() != null) {
            throw new BadRequestAlertException("A new categorieActivite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieActivite result = categorieActiviteService.save(categorieActivite);
        return ResponseEntity.created(new URI("/api/categorie-activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categorie-activites : Updates an existing categorieActivite.
     *
     * @param categorieActivite the categorieActivite to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categorieActivite,
     * or with status 400 (Bad Request) if the categorieActivite is not valid,
     * or with status 500 (Internal Server Error) if the categorieActivite couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/categorie-activites")
    @Timed
    public ResponseEntity<CategorieActivite> updateCategorieActivite(@RequestBody CategorieActivite categorieActivite) throws URISyntaxException {
        log.debug("REST request to update CategorieActivite : {}", categorieActivite);
        if (categorieActivite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieActivite result = categorieActiviteService.save(categorieActivite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categorieActivite.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categorie-activites : get all the categorieActivites.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categorieActivites in body
     */
    @GetMapping("/categorie-activites")
    @Timed
    public List<CategorieActivite> getAllCategorieActivites() {
        log.debug("REST request to get all CategorieActivites");
        return categorieActiviteService.findAll();
    }

    /**
     * GET  /categorie-activites/:id : get the "id" categorieActivite.
     *
     * @param id the id of the categorieActivite to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categorieActivite, or with status 404 (Not Found)
     */
    @GetMapping("/categorie-activites/{id}")
    @Timed
    public ResponseEntity<CategorieActivite> getCategorieActivite(@PathVariable Long id) {
        log.debug("REST request to get CategorieActivite : {}", id);
        Optional<CategorieActivite> categorieActivite = categorieActiviteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieActivite);
    }

    /**
     * DELETE  /categorie-activites/:id : delete the "id" categorieActivite.
     *
     * @param id the id of the categorieActivite to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/categorie-activites/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategorieActivite(@PathVariable Long id) {
        log.debug("REST request to delete CategorieActivite : {}", id);
        categorieActiviteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
