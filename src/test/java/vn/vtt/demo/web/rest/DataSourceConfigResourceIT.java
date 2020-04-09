package vn.vtt.demo.web.rest;

import vn.vtt.demo.domain.DataSourceConfig;
import vn.vtt.demo.repository.DataSourceConfigRepository;
import vn.vtt.demo.service.DataSourceConfigService;
import vn.vtt.demo.service.dto.DataSourceConfigDTO;
import vn.vtt.demo.service.mapper.DataSourceConfigMapper;
import vn.vtt.demo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static vn.vtt.demo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DataSourceConfigResource} REST controller.
 */
@SpringBootTest(classes = DataSourceConfigResource.class)
public class DataSourceConfigResourceIT {

    private static final String DEFAULT_DRIVERCLASSNAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVERCLASSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INITIALIZE = false;
    private static final Boolean UPDATED_INITIALIZE = true;

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

    @Autowired
    private DataSourceConfigMapper dataSourceConfigMapper;

    @Autowired
    private DataSourceConfigService dataSourceConfigService;

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

    private MockMvc restDataSourceConfigMockMvc;

    private DataSourceConfig dataSourceConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataSourceConfigResource dataSourceConfigResource = new DataSourceConfigResource(dataSourceConfigService);
        this.restDataSourceConfigMockMvc = MockMvcBuilders.standaloneSetup(dataSourceConfigResource)
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
    public static DataSourceConfig createEntity(EntityManager em) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
            .driverclassname(DEFAULT_DRIVERCLASSNAME)
            .url(DEFAULT_URL)
            .name(DEFAULT_NAME)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .initialize(DEFAULT_INITIALIZE);
        return dataSourceConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataSourceConfig createUpdatedEntity(EntityManager em) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
            .driverclassname(UPDATED_DRIVERCLASSNAME)
            .url(UPDATED_URL)
            .name(UPDATED_NAME)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .initialize(UPDATED_INITIALIZE);
        return dataSourceConfig;
    }

    @BeforeEach
    public void initTest() {
        dataSourceConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataSourceConfig() throws Exception {
        int databaseSizeBeforeCreate = dataSourceConfigRepository.findAll().size();

        // Create the DataSourceConfig
        DataSourceConfigDTO dataSourceConfigDTO = dataSourceConfigMapper.toDto(dataSourceConfig);
        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeCreate + 1);
        DataSourceConfig testDataSourceConfig = dataSourceConfigList.get(dataSourceConfigList.size() - 1);
        assertThat(testDataSourceConfig.getDriverclassname()).isEqualTo(DEFAULT_DRIVERCLASSNAME);
        assertThat(testDataSourceConfig.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testDataSourceConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDataSourceConfig.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testDataSourceConfig.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testDataSourceConfig.isInitialize()).isEqualTo(DEFAULT_INITIALIZE);
    }

    @Test
    @Transactional
    public void createDataSourceConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataSourceConfigRepository.findAll().size();

        // Create the DataSourceConfig with an existing ID
        dataSourceConfig.setId(1L);
        DataSourceConfigDTO dataSourceConfigDTO = dataSourceConfigMapper.toDto(dataSourceConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataSourceConfigs() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        // Get all the dataSourceConfigList
        restDataSourceConfigMockMvc.perform(get("/api/data-source-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataSourceConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverclassname").value(hasItem(DEFAULT_DRIVERCLASSNAME)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].initialize").value(hasItem(DEFAULT_INITIALIZE.booleanValue())));
    }

    @Test
    @Transactional
    public void getDataSourceConfig() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        // Get the dataSourceConfig
        restDataSourceConfigMockMvc.perform(get("/api/data-source-configs/{id}", dataSourceConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dataSourceConfig.getId().intValue()))
            .andExpect(jsonPath("$.driverclassname").value(DEFAULT_DRIVERCLASSNAME))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.initialize").value(DEFAULT_INITIALIZE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDataSourceConfig() throws Exception {
        // Get the dataSourceConfig
        restDataSourceConfigMockMvc.perform(get("/api/data-source-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataSourceConfig() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        int databaseSizeBeforeUpdate = dataSourceConfigRepository.findAll().size();

        // Update the dataSourceConfig
        DataSourceConfig updatedDataSourceConfig = dataSourceConfigRepository.findById(dataSourceConfig.getId()).get();
        // Disconnect from session so that the updates on updatedDataSourceConfig are not directly saved in db
        em.detach(updatedDataSourceConfig);
        updatedDataSourceConfig
            .driverclassname(UPDATED_DRIVERCLASSNAME)
            .url(UPDATED_URL)
            .name(UPDATED_NAME)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .initialize(UPDATED_INITIALIZE);
        DataSourceConfigDTO dataSourceConfigDTO = dataSourceConfigMapper.toDto(updatedDataSourceConfig);

        restDataSourceConfigMockMvc.perform(put("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfigDTO)))
            .andExpect(status().isOk());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeUpdate);
        DataSourceConfig testDataSourceConfig = dataSourceConfigList.get(dataSourceConfigList.size() - 1);
        assertThat(testDataSourceConfig.getDriverclassname()).isEqualTo(UPDATED_DRIVERCLASSNAME);
        assertThat(testDataSourceConfig.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testDataSourceConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDataSourceConfig.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testDataSourceConfig.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testDataSourceConfig.isInitialize()).isEqualTo(UPDATED_INITIALIZE);
    }

    @Test
    @Transactional
    public void updateNonExistingDataSourceConfig() throws Exception {
        int databaseSizeBeforeUpdate = dataSourceConfigRepository.findAll().size();

        // Create the DataSourceConfig
        DataSourceConfigDTO dataSourceConfigDTO = dataSourceConfigMapper.toDto(dataSourceConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataSourceConfigMockMvc.perform(put("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataSourceConfig() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        int databaseSizeBeforeDelete = dataSourceConfigRepository.findAll().size();

        // Delete the dataSourceConfig
        restDataSourceConfigMockMvc.perform(delete("/api/data-source-configs/{id}", dataSourceConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
