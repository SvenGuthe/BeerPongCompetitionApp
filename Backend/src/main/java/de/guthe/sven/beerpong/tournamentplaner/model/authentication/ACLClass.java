package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "acl_class")
public class ACLClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "class")
    private String aclClass;

    @OneToMany(mappedBy = "aclClass")
    private List<ACLObjectIdentity> aclObjectIdentities;

    public ACLClass() {
    }

    public Long getId() {
        return id;
    }

    public String getAclClass() {
        return aclClass;
    }

    public void setAclClass(String aclClass) {
        this.aclClass = aclClass;
    }

    public List<ACLObjectIdentity> getAclObjectIdentities() {
        return aclObjectIdentities;
    }

    public void setAclObjectIdentities(List<ACLObjectIdentity> aclObjectIdentities) {
        this.aclObjectIdentities = aclObjectIdentities;
    }
}
