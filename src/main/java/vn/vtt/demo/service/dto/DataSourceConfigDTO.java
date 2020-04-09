package vn.vtt.demo.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vtt.demo.domain.DataSourceConfig} entity.
 */
public class DataSourceConfigDTO implements Serializable {

    private Long id;

    private String driverclassname;

    private String url;

    private String name;

    private String username;

    private String password;

    private Boolean initialize;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverclassname() {
        return driverclassname;
    }

    public void setDriverclassname(String driverclassname) {
        this.driverclassname = driverclassname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isInitialize() {
        return initialize;
    }

    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataSourceConfigDTO dataSourceConfigDTO = (DataSourceConfigDTO) o;
        if (dataSourceConfigDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataSourceConfigDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataSourceConfigDTO{" +
            "id=" + getId() +
            ", driverclassname='" + getDriverclassname() + "'" +
            ", url='" + getUrl() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", initialize='" + isInitialize() + "'" +
            "}";
    }
}
