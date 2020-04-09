package vn.vtt.demo.service.mapper;

import vn.vtt.demo.domain.*;
import vn.vtt.demo.service.dto.DataSourceConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataSourceConfig} and its DTO {@link DataSourceConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataSourceConfigMapper extends EntityMapper<DataSourceConfigDTO, DataSourceConfig> {



    default DataSourceConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setId(id);
        return dataSourceConfig;
    }
}
