package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.InstancesApp;

import io.github.jhipster.application.domain.CategorieActivite;
import io.github.jhipster.application.repository.CategorieActiviteRepository;
import io.github.jhipster.application.service.CategorieActiviteService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CategorieActiviteResource REST controller.
 *
 * @see CategorieActiviteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InstancesApp.class)
public class CategorieActiviteResourceIntTest {

    private static final String DEFAULT_CATEGORIE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIE = "BBBBBBBBBB";

    @Autowired
    private CategorieActiviteRepository categorieActiviteRepository;

    @Autowired
    private CategorieActiviteService categorieActiviteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCategorieActiviteMockMvc;

    private CategorieActivite categorieActivite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategorieActiviteResource categorieActiviteResource = new CategorieActiviteResource(categorieActiviteService);
        this.restCategorieActiviteMockMvc = MockMvcBuilders.standaloneSetup(categorieActiviteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieActivite createEntity(EntityManager em) {
        CategorieActivite categorieActivite = new CategorieActivite()
            .categorie(DEFAULT_CATEGORIE);
        return categorieActivite;
    }

    @Before
    public void initTest() {
        categorieActivite = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieActivite() throws Exception {
        int databaseSizeBeforeCreate = categorieActiviteRepository.findAll().size();

        // Create the CategorieActivite
        restCategorieActiviteMockMvc.perform(post("/api/categorie-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieActivite)))
            .andExpect(status().isCreated());

        // Validate the CategorieActivite in the database
        List<CategorieActivite> categorieActiviteList = categorieActiviteRepository.findAll();
        assertThat(categorieActiviteList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieActivite testCategorieActivite = categorieActiviteList.get(categorieActiviteList.size() - 1);
        assertThat(testCategorieActivite.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
    }

    @Test
    @Transactional
    public void createCategorieActiviteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieActiviteRepository.findAll().size();

        // Create the CategorieActivite with an existing ID
        categorieActivite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieActiviteMockMvc.perform(post("/api/categorie-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieActivite)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieActivite in the database
        List<CategorieActivite> categorieActiviteList = categorieActiviteRepository.findAll();
        assertThat(categorieActiviteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCategorieActivites() throws Exception {
        // Initialize the database
        categorieActiviteRepository.saveAndFlush(categorieActivite);

        // Get all the categorieActiviteList
        restCategorieActiviteMockMvc.perform(get("/api/categorie-activites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieActivite.getId().intValue())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())));
    }
    
    @Test
    @Transactional
    public void getCategorieActivite() throws Exception {
        // Initialize the database
        categorieActiviteRepository.saveAndFlush(categorieActivite);

        // Get the categorieActivite
        restCategorieActiviteMockMvc.perform(get("/api/categorie-activites/{id}", categorieActivite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categorieActivite.getId().intValue()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategorieActivite() throws Exception {
        // Get the categorieActivite
        restCategorieActiviteMockMvc.perform(get("/api/categorie-activites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieActivite() throws Exception {
        // Initialize the database
        categorieActiviteService.save(categorieActivite);

        int databaseSizeBeforeUpdate = categorieActiviteRepository.findAll().size();

        // Update the categorieActivite
        CategorieActivite updatedCategorieActivite = categorieActiviteRepository.findById(categorieActivite.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieActivite are not directly saved in db
        em.detach(updatedCategorieActivite);
        updatedCategorieActivite
            .categorie(UPDATED_CATEGORIE);

        restCategorieActiviteMockMvc.perform(put("/api/categorie-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategorieActivite)))
            .andExpect(status().isOk());

        // Validate the CategorieActivite in the database
        List<CategorieActivite> categorieActiviteList = categorieActiviteRepository.findAll();
        assertThat(categorieActiviteList).hasSize(databaseSizeBeforeUpdate);
        CategorieActivite testCategorieActivite = categorieActiviteList.get(categorieActiviteList.size() - 1);
        assertThat(testCategorieActivite.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieActivite() throws Exception {
        int databaseSizeBeforeUpdate = categorieActiviteRepository.findAll().size();

        // Create the CategorieActivite

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieActiviteMockMvc.perform(put("/api/categorie-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieActivite)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieActivite in the database
        List<CategorieActivite> categorieActiviteList = categorieActiviteRepository.findAll();
        assertThat(categorieActiviteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieActivite() throws Exception {
        // Initialize the database
        categorieActiviteService.save(categorieActivite);

        int databaseSizeBeforeDelete = categorieActiviteRepository.findAll().size();

        // Get the categorieActivite
        restCategorieActiviteMockMvc.perform(delete("/api/categorie-activites/{id}", categorieActivite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CategorieActivite> categorieActiviteList = categorieActiviteRepository.findAll();
        assertThat(categorieActiviteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieActivite.class);
        CategorieActivite categorieActivite1 = new CategorieActivite();
        categorieActivite1.setId(1L);
        CategorieActivite categorieActivite2 = new CategorieActivite();
        categorieActivite2.setId(categorieActivite1.getId());
        assertThat(categorieActivite1).isEqualTo(categorieActivite2);
        categorieActivite2.setId(2L);
        assertThat(categorieActivite1).isNotEqualTo(categorieActivite2);
        categorieActivite1.setId(null);
        assertThat(categorieActivite1).isNotEqualTo(categorieActivite2);
    }
}
