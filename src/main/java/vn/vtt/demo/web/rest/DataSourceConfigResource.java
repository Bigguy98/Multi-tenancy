package vn.vtt.demo.web.rest;

import vn.vtt.demo.service.DataSourceConfigService;
import vn.vtt.demo.web.rest.errors.BadRequestAlertException;
import vn.vtt.demo.service.dto.DataSourceConfigDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.vtt.demo.domain.DataSourceConfig}.
 */
@RestController
@RequestMapping("/api")
public class DataSourceConfigResource {

    private final Logger log = LoggerFactory.getLogger(DataSourceConfigResource.class);

    private static final String ENTITY_NAME = "dataSourceConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataSourceConfigService dataSourceConfigService;

    public DataSourceConfigResource(DataSourceConfigService dataSourceConfigService) {
        this.dataSourceConfigService = dataSourceConfigService;
    }

    /**
     * {@code POST  /data-source-configs} : Create a new dataSourceConfig.
     *
     * @param dataSourceConfigDTO the dataSourceConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataSourceConfigDTO, or with status {@code 400 (Bad Request)} if the dataSourceConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-source-configs")
    public ResponseEntity<DataSourceConfigDTO> createDataSourceConfig(@RequestBody DataSourceConfigDTO dataSourceConfigDTO) throws URISyntaxException {
        log.debug("REST request to save DataSourceConfig : {}", dataSourceConfigDTO);
        if (dataSourceConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataSourceConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataSourceConfigDTO result = dataSourceConfigService.save(dataSourceConfigDTO);
        return ResponseEntity.created(new URI("/api/data-source-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-source-configs} : Updates an existing dataSourceConfig.
     *
     * @param dataSourceConfigDTO the dataSourceConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataSourceConfigDTO,
     * or with status {@code 400 (Bad Request)} if the dataSourceConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataSourceConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-source-configs")
    public ResponseEntity<DataSourceConfigDTO> updateDataSourceConfig(@RequestBody DataSourceConfigDTO dataSourceConfigDTO) throws URISyntaxException {
        log.debug("REST request to update DataSourceConfig : {}", dataSourceConfigDTO);
        if (dataSourceConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataSourceConfigDTO result = dataSourceConfigService.save(dataSourceConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataSourceConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-source-configs} : get all the dataSourceConfigs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataSourceConfigs in body.
     */
    @GetMapping("/data-source-configs")
    public List<DataSourceConfigDTO> getAllDataSourceConfigs() {
        log.debug("REST request to get all DataSourceConfigs");
        return dataSourceConfigService.findAll();
    }

    /**
     * {@code GET  /data-source-configs/:id} : get the "id" dataSourceConfig.
     *
     * @param id the id of the dataSourceConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataSourceConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-source-configs/{id}")
    public ResponseEntity<DataSourceConfigDTO> getDataSourceConfig(@PathVariable Long id) {
        log.debug("REST request to get DataSourceConfig : {}", id);
        Optional<DataSourceConfigDTO> dataSourceConfigDTO = dataSourceConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataSourceConfigDTO);
    }

    /**
     * {@code DELETE  /data-source-configs/:id} : delete the "id" dataSourceConfig.
     *
     * @param id the id of the dataSourceConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-source-configs/{id}")
    public ResponseEntity<Void> deleteDataSourceConfig(@PathVariable Long id) {
        log.debug("REST request to delete DataSourceConfig : {}", id);
        dataSourceConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
