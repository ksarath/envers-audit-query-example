package com.envers.domain.vo;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity(name = "ORGANISATIONS")
@Audited
public class OrganisationVO {

    @Id
    private Long id;

    @Column(nullable = false, name = "NAME", length = 70)
    private String name;

    public OrganisationVO() {
    }

    public OrganisationVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof OrganisationVO)) return false;

        OrganisationVO that = (OrganisationVO) obj;
        if(that.id.equals(this.id) && that.name.equals(this.name)) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id.hashCode();
        result = 31 * result + this.name.hashCode();
        return result;
    }
}