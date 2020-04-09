package vn.vtt.demo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DataSourceConfigMapperTest {

    private DataSourceConfigMapper dataSourceConfigMapper;

    @BeforeEach
    public void setUp() {
        dataSourceConfigMapper = new DataSourceConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dataSourceConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataSourceConfigMapper.fromId(null)).isNull();
    }
}
