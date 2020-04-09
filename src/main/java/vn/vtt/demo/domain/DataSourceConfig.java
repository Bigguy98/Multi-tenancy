package vn.vtt.demo.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataSourceConfig.
 */
@Entity
@Table(name = "datasourceconfig")
public class DataSourceConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "driverclassname")
    private String driverclassname;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "initialize")
    private Boolean initialize;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverclassname() {
        return driverclassname;
    }

    public DataSourceConfig driverclassname(String driverclassname) {
        this.driverclassname = driverclassname;
        return this;
    }

    public void setDriverclassname(String driverclassname) {
        this.driverclassname = driverclassname;
    }

    public String getUrl() {
        return url;
    }

    public DataSourceConfig url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public DataSourceConfig name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceConfig username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceConfig password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isInitialize() {
        return initialize;
    }

    public DataSourceConfig initialize(Boolean initialize) {
        this.initialize = initialize;
        return this;
    }

    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataSourceConfig)) {
            return false;
        }
        return id != null && id.equals(((DataSourceConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
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
