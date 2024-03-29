package de.guthe.sven.beerpong.tournamentplaner.model.authorization;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "acl_sid")
@NamedQuery(name = "ACLSid.findByEmail",
		query = "SELECT a FROM ACLSid a WHERE LOWER(a.sid) = LOWER(?1) AND a.principle = true")
public class ACLSid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "principal")
	private Boolean principle;

	@Column(name = "sid")
	private String sid;

	@OneToMany(mappedBy = "aclSid", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ACLEntry> aclEntries;

	@OneToMany(mappedBy = "aclSid", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ACLObjectIdentity> aclObjectIdentities;

	public ACLSid() {
	}

	public Long getId() {
		return id;
	}

	public Boolean getPrinciple() {
		return principle;
	}

	public void setPrinciple(Boolean principle) {
		this.principle = principle;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public List<ACLEntry> getAclEntries() {
		return aclEntries;
	}

	public void setAclEntries(List<ACLEntry> aclEntries) {
		this.aclEntries = aclEntries;
	}

	public List<ACLObjectIdentity> getAclObjectIdentities() {
		return aclObjectIdentities;
	}

	public void setAclObjectIdentities(List<ACLObjectIdentity> aclObjectIdentities) {
		this.aclObjectIdentities = aclObjectIdentities;
	}

	@Override
	public String toString() {
		return "ACLSid{" + "id=" + id + ", principle=" + principle + ", sid='" + sid + '\'' + ", aclEntries="
				+ aclEntries + ", aclObjectIdentities=" + aclObjectIdentities + '}';
	}

}
