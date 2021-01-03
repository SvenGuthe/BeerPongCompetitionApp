package de.guthe.sven.beerpong.tournamentplaner.model.authorization;

import javax.persistence.*;

@Entity
@Table(name = "acl_entry")
@NamedQuery(name = "ACLEntry.findMasksByACLObjectIdentityACLSid", query = "SELECT a.mask FROM ACLEntry a WHERE LOWER(a.objectIdentity.id) = LOWER(?1) AND LOWER(a.aclSid.id) = LOWER(?2)")
public class ACLEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acl_object_identity", nullable = false)
    private ACLObjectIdentity objectIdentity;

    @Column(name = "ace_order", nullable = false)
    private Integer aceOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sid", nullable = false)
    private ACLSid aclSid;

    @Column(name = "mask", nullable = false)
    private Integer mask;

    @Column(name = "granting", nullable = false)
    private Boolean granting;

    @Column(name = "audit_success", nullable = false)
    private Boolean auditSuccess;

    @Column(name = "audit_failure", nullable = false)
    private Boolean auditFailure;

    public ACLEntry() {
    }

    public Long getId() {
        return id;
    }

    public ACLObjectIdentity getObjectIdentity() {
        return objectIdentity;
    }

    public void setObjectIdentity(ACLObjectIdentity objectIdentity) {
        this.objectIdentity = objectIdentity;
    }

    public Integer getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aclOrder) {
        this.aceOrder = aclOrder;
    }

    public ACLSid getAclSid() {
        return aclSid;
    }

    public void setAclSid(ACLSid aclSid) {
        this.aclSid = aclSid;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

}
