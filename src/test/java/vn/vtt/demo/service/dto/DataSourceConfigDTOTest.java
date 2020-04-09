package vn.vtt.demo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.vtt.demo.web.rest.TestUtil;

public class DataSourceConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataSourceConfigDTO.class);
        DataSourceConfigDTO dataSourceConfigDTO1 = new DataSourceConfigDTO();
        dataSourceConfigDTO1.setId(1L);
        DataSourceConfigDTO dataSourceConfigDTO2 = new DataSourceConfigDTO();
        assertThat(dataSourceConfigDTO1).isNotEqualTo(dataSourceConfigDTO2);
        dataSourceConfigDTO2.setId(dataSourceConfigDTO1.getId());
        assertThat(dataSourceConfigDTO1).isEqualTo(dataSourceConfigDTO2);
        dataSourceConfigDTO2.setId(2L);
        assertThat(dataSourceConfigDTO1).isNotEqualTo(dataSourceConfigDTO2);
        dataSourceConfigDTO1.setId(null);
        assertThat(dataSourceConfigDTO1).isNotEqualTo(dataSourceConfigDTO2);
    }
}
