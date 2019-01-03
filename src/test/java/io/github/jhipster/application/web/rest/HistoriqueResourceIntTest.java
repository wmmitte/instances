package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.InstancesApp;

import io.github.jhipster.application.domain.Historique;
import io.github.jhipster.application.repository.HistoriqueRepository;
import io.github.jhipster.application.service.HistoriqueService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HistoriqueResource REST controller.
 *
 * @see HistoriqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InstancesApp.class)
public class HistoriqueResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    @Autowired
    private HistoriqueRepository historiqueRepository;

    @Autowired
    private HistoriqueService historiqueService;

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

    private MockMvc restHistoriqueMockMvc;

    private Historique historique;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HistoriqueResource historiqueResource = new HistoriqueResource(historiqueService);
        this.restHistoriqueMockMvc = MockMvcBuilders.standaloneSetup(historiqueResource)
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
    public static Historique createEntity(EntityManager em) {
        Historique historique = new Historique()
            .date(DEFAULT_DATE)
            .objet(DEFAULT_OBJET);
        return historique;
    }

    @Before
    public void initTest() {
        historique = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistorique() throws Exception {
        int databaseSizeBeforeCreate = historiqueRepository.findAll().size();

        // Create the Historique
        restHistoriqueMockMvc.perform(post("/api/historiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historique)))
            .andExpect(status().isCreated());

        // Validate the Historique in the database
        List<Historique> historiqueList = historiqueRepository.findAll();
        assertThat(historiqueList).hasSize(databaseSizeBeforeCreate + 1);
        Historique testHistorique = historiqueList.get(historiqueList.size() - 1);
        assertThat(testHistorique.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testHistorique.getObjet()).isEqualTo(DEFAULT_OBJET);
    }

    @Test
    @Transactional
    public void createHistoriqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historiqueRepository.findAll().size();

        // Create the Historique with an existing ID
        historique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoriqueMockMvc.perform(post("/api/historiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historique)))
            .andExpect(status().isBadRequest());

        // Validate the Historique in the database
        List<Historique> historiqueList = historiqueRepository.findAll();
        assertThat(historiqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHistoriques() throws Exception {
        // Initialize the database
        historiqueRepository.saveAndFlush(historique);

        // Get all the historiqueList
        restHistoriqueMockMvc.perform(get("/api/historiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historique.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET.toString())));
    }
    
    @Test
    @Transactional
    public void getHistorique() throws Exception {
        // Initialize the database
        historiqueRepository.saveAndFlush(historique);

        // Get the historique
        restHistoriqueMockMvc.perform(get("/api/historiques/{id}", historique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historique.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHistorique() throws Exception {
        // Get the historique
        restHistoriqueMockMvc.perform(get("/api/historiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistorique() throws Exception {
        // Initialize the database
        historiqueService.save(historique);

        int databaseSizeBeforeUpdate = historiqueRepository.findAll().size();

        // Update the historique
        Historique updatedHistorique = historiqueRepository.findById(historique.getId()).get();
        // Disconnect from session so that the updates on updatedHistorique are not directly saved in db
        em.detach(updatedHistorique);
        updatedHistorique
            .date(UPDATED_DATE)
            .objet(UPDATED_OBJET);

        restHistoriqueMockMvc.perform(put("/api/historiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHistorique)))
            .andExpect(status().isOk());

        // Validate the Historique in the database
        List<Historique> historiqueList = historiqueRepository.findAll();
        assertThat(historiqueList).hasSize(databaseSizeBeforeUpdate);
        Historique testHistorique = historiqueList.get(historiqueList.size() - 1);
        assertThat(testHistorique.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHistorique.getObjet()).isEqualTo(UPDATED_OBJET);
    }

    @Test
    @Transactional
    public void updateNonExistingHistorique() throws Exception {
        int databaseSizeBeforeUpdate = historiqueRepository.findAll().size();

        // Create the Historique

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoriqueMockMvc.perform(put("/api/historiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historique)))
            .andExpect(status().isBadRequest());

        // Validate the Historique in the database
        List<Historique> historiqueList = historiqueRepository.findAll();
        assertThat(historiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistorique() throws Exception {
        // Initialize the database
        historiqueService.save(historique);

        int databaseSizeBeforeDelete = historiqueRepository.findAll().size();

        // Get the historique
        restHistoriqueMockMvc.perform(delete("/api/historiques/{id}", historique.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Historique> historiqueList = historiqueRepository.findAll();
        assertThat(historiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Historique.class);
        Historique historique1 = new Historique();
        historique1.setId(1L);
        Historique historique2 = new Historique();
        historique2.setId(historique1.getId());
        assertThat(historique1).isEqualTo(historique2);
        historique2.setId(2L);
        assertThat(historique1).isNotEqualTo(historique2);
        historique1.setId(null);
        assertThat(historique1).isNotEqualTo(historique2);
    }
}
