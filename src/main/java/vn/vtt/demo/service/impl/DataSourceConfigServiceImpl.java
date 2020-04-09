package vn.vtt.demo.service.impl;

import vn.vtt.demo.service.DataSourceConfigService;
import vn.vtt.demo.domain.DataSourceConfig;
import vn.vtt.demo.repository.DataSourceConfigRepository;
import vn.vtt.demo.service.dto.DataSourceConfigDTO;
import vn.vtt.demo.service.mapper.DataSourceConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DataSourceConfig}.
 */
@Service
@Transactional
public class DataSourceConfigServiceImpl implements DataSourceConfigService {

    private final Logger log = LoggerFactory.getLogger(DataSourceConfigServiceImpl.class);

    private final DataSourceConfigRepository dataSourceConfigRepository;

    private final DataSourceConfigMapper dataSourceConfigMapper;

    public DataSourceConfigServiceImpl(DataSourceConfigRepository dataSourceConfigRepository, DataSourceConfigMapper dataSourceConfigMapper) {
        this.dataSourceConfigRepository = dataSourceConfigRepository;
        this.dataSourceConfigMapper = dataSourceConfigMapper;
    }

    /**
     * Save a dataSourceConfig.
     *
     * @param dataSourceConfigDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DataSourceConfigDTO save(DataSourceConfigDTO dataSourceConfigDTO) {
        log.debug("Request to save DataSourceConfig : {}", dataSourceConfigDTO);
        DataSourceConfig dataSourceConfig = dataSourceConfigMapper.toEntity(dataSourceConfigDTO);
        dataSourceConfig = dataSourceConfigRepository.save(dataSourceConfig);
        return dataSourceConfigMapper.toDto(dataSourceConfig);
    }

    /**
     * Get all the dataSourceConfigs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DataSourceConfigDTO> findAll() {
        log.debug("Request to get all DataSourceConfigs");
        return dataSourceConfigRepository.findAll().stream()
            .map(dataSourceConfigMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dataSourceConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DataSourceConfigDTO> findOne(Long id) {
        log.debug("Request to get DataSourceConfig : {}", id);
        return dataSourceConfigRepository.findById(id)
            .map(dataSourceConfigMapper::toDto);
    }

    /**
     * Delete the dataSourceConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataSourceConfig : {}", id);
        dataSourceConfigRepository.deleteById(id);
    }
}
