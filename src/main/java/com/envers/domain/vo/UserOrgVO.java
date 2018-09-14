package com.envers.domain.vo;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity(name = "USER_ORGANISATIONS")
@Audited
public class UserOrgVO {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column (name = "USER_ID")
    private Long userid;

    @ManyToOne
    @JoinColumn (name = "ORGANISATION_ID", nullable = false)
    private OrganisationVO organisation;

    @Column (name = "PREFERRED", nullable = false)
    private boolean preferred = false;

    public UserOrgVO() {
    }

    public UserOrgVO(Long id, Long userid, OrganisationVO organisation, boolean preferred) {
        this.id = id;
        this.userid = userid;
        this.organisation = organisation;
        this.preferred = preferred;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
                that.userid.equals(this.userid) &&
                that.preferred == this.preferred &&
                that.organisation.equals(this.organisation)
        ) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id.hashCode();
        result = 31 * result + this.userid.hashCode();
        result = 31 * result + this.organisation.hashCode();
        return result;
    }
}
