package de.guthe.sven.beerpong.tournamentplaner.model.authorization;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "acl_class")
@NamedQuery(name = "ACLClass.findByClassName", query = "SELECT a FROM ACLClass a WHERE LOWER(a.aclClass) = LOWER(?1)")
public class ACLClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "class")
	private String aclClass;

	@OneToMany(mappedBy = "aclClass", fetch = FetchType.LAZY)
	@JsonIgnore
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

	@Override
	public String toString() {
		return "ACLClass{" + "id=" + id + ", aclClass='" + aclClass + '\'' + ", aclObjectIdentities="
				+ aclObjectIdentities + '}';
	}

}
