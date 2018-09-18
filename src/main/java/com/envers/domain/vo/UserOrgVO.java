package com.envers.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity(name = "USER_ORGANISATIONS")
@Audited
public class UserOrgVO {
    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "USER_ID", nullable = false)
    @JsonIgnore
    private UserVO user;

    @ManyToOne
    @JoinColumn (name = "ORGANISATION_ID", nullable = false)
    private OrganisationVO organisation;

    @Column (name = "PREFERRED", nullable = false)
    private boolean preferred = false;

    public UserOrgVO() {
    }

    public UserOrgVO(Long id, UserVO user, OrganisationVO organisation, boolean preferred) {
        this.id = id;
        this.user = user;
        this.organisation = organisation;
        this.preferred = preferred;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public OrganisationVO getOrganisation() {
        return organisation;
    }

    public void setOrganisation(OrganisationVO organisation) {
        this.organisation = organisation;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof UserOrgVO)) return false;

        UserOrgVO that = (UserOrgVO) obj;
        if(that.id.equals(this.id) &&
                that.user.getId().equals(this.user.getId()) &&
                that.preferred == this.preferred &&
                that.organisation.equals(this.organisation)
        ) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id.hashCode();
        result = 31 * result + this.user.getId().hashCode();
        return result;
    }
}
