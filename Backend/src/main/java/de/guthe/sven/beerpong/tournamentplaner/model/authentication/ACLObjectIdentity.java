package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "acl_object_identity")
public class ACLObjectIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id_class", nullable = false)
    private ACLClass aclClass;

    @Column(name = "object_id_identity", nullable = false)
    private Long objectIdIdentitiy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_object")
    private ACLObjectIdentity parentObject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_sid")
    private ACLSid aclSid;

    @Column(name = "entries_inheriting", nullable = false)
    private Boolean entriesInheriting;

    @OneToMany(mappedBy = "objectIdentity")
    private List<ACLEntry> aclEntries;

    public ACLObjectIdentity() {
    }

    public Long getId() {
        return id;
    }

    public ACLClass getAclClass() {
        return aclClass;
    }

    public void setAclClass(ACLClass aclClass) {
        this.aclClass = aclClass;
    }

    public Long getObjectIdIdentitiy() {
        return objectIdIdentitiy;
    }

    public void setObjectIdIdentitiy(Long objectIdIdentitiy) {
        this.objectIdIdentitiy = objectIdIdentitiy;
    }

    public ACLObjectIdentity getParentObject() {
        return parentObject;
    }

    public void setParentObject(ACLObjectIdentity parentObject) {
        this.parentObject = parentObject;
    }

    public ACLSid getAclSid() {
        return aclSid;
    }

    public void setAclSid(ACLSid aclSid) {
        this.aclSid = aclSid;
    }

    public Boolean getEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(Boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }

    public List<ACLEntry> getAclEntries() {
        return aclEntries;
    }

    public void setAclEntries(List<ACLEntry> aclEntries) {
        this.aclEntries = aclEntries;
    }
}
