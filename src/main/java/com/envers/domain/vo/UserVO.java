package com.envers.domain.vo;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USERS")
@Audited
public class UserVO {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "userid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited
    private List<UserOrgVO> organisations;

    public UserVO() {
    }

    public UserVO(Long id, String name, List<UserOrgVO> organisations) {
        this.id = id;
        this.name = name;
        this.organisations = organisations;
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

    public List<UserOrgVO> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<UserOrgVO> organisations) {
        this.organisations = organisations;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof UserVO)) return false;

        UserVO that = (UserVO) obj;
        if(that.id.equals(this.id) &&
                that.name.equals(this.name) &&
                that.organisations.containsAll(this.organisations) &&
                this.organisations.containsAll(that.organisations)
        ) return true;
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
