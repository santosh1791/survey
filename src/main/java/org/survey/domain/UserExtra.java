package org.survey.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A UserExtra.
 */
@Entity
@Table(name = "user_extra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    //@SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "notifications_enabled")
    private Boolean notificationsEnabled;

    @Column(name = "notifications_filter")
    private Integer notificationsFilter;

    @Size(max = 5)
    @Column(name = "language", length = 5)
    private String language;

    @Size(max = 255)
    @Column(name = "token", length = 255)
    private String token;

    @Column(name = "lock_expiration_date")
    private ZonedDateTime lockExpirationDate;

    @Column(name = "jhi_failed_login_attempts")
    private Integer failedLoginAttempts;

    @Column(name = "project_admin")
    private Boolean projectAdmin;

    @Size(max = 50)
    @Column(name = "jhi_role", length = 50)
    private String role;

    @Size(max = 255)
    @Column(name = "apiaccesstoken", length = 255)
    private String apiaccesstoken;
    
    @MapsId
    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public UserExtra notificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
        return this;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public Integer getNotificationsFilter() {
        return notificationsFilter;
    }

    public UserExtra notificationsFilter(Integer notificationsFilter) {
        this.notificationsFilter = notificationsFilter;
        return this;
    }

    public void setNotificationsFilter(Integer notificationsFilter) {
        this.notificationsFilter = notificationsFilter;
    }

    public String getLanguage() {
        return language;
    }

    public UserExtra language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getToken() {
        return token;
    }

    public UserExtra token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getLockExpirationDate() {
        return lockExpirationDate;
    }

    public UserExtra lockExpirationDate(ZonedDateTime lockExpirationDate) {
        this.lockExpirationDate = lockExpirationDate;
        return this;
    }

    public void setLockExpirationDate(ZonedDateTime lockExpirationDate) {
        this.lockExpirationDate = lockExpirationDate;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public UserExtra failedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
        return this;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Boolean isProjectAdmin() {
        return projectAdmin;
    }

    public UserExtra projectAdmin(Boolean projectAdmin) {
        this.projectAdmin = projectAdmin;
        return this;
    }

    public void setProjectAdmin(Boolean projectAdmin) {
        this.projectAdmin = projectAdmin;
    }

    public String getRole() {
        return role;
    }

    public UserExtra role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApiaccesstoken() {
        return apiaccesstoken;
    }

    public UserExtra apiaccesstoken(String apiaccesstoken) {
        this.apiaccesstoken = apiaccesstoken;
        return this;
    }

    public void setApiaccesstoken(String apiaccesstoken) {
        this.apiaccesstoken = apiaccesstoken;
    }

    public User getUser() {
        return user;
    }

    public UserExtra user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserExtra userExtra = (UserExtra) o;
        if (userExtra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtra{" +
            "id=" + getId() +
            ", notificationsEnabled='" + isNotificationsEnabled() + "'" +
            ", notificationsFilter='" + getNotificationsFilter() + "'" +
            ", language='" + getLanguage() + "'" +
            ", token='" + getToken() + "'" +
            ", lockExpirationDate='" + getLockExpirationDate() + "'" +
            ", failedLoginAttempts='" + getFailedLoginAttempts() + "'" +
            ", projectAdmin='" + isProjectAdmin() + "'" +
            ", role='" + getRole() + "'" +
            ", apiaccesstoken='" + getApiaccesstoken() + "'" +
            "}";
    }
}
