package vn.vtt.demo.service;

import vn.vtt.demo.service.dto.DataSourceConfigDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.vtt.demo.domain.DataSourceConfig}.
 */
public interface DataSourceConfigService {

    /**
     * Save a dataSourceConfig.
     *
     * @param dataSourceConfigDTO the entity to save.
     * @return the persisted entity.
     */
    DataSourceConfigDTO save(DataSourceConfigDTO dataSourceConfigDTO);

    /**
     * Get all the dataSourceConfigs.
     *
     * @return the list of entities.
     */
    List<DataSourceConfigDTO> findAll();


    /**
     * Get the "id" dataSourceConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataSourceConfigDTO> findOne(Long id);

    /**
     * Delete the "id" dataSourceConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
