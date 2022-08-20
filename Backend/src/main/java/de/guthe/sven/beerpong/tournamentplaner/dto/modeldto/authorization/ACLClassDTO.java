package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authorization;

import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;

import javax.validation.constraints.NotNull;

public class ACLClassDTO extends EnumDTO {

	@NotNull(message = "aclClass in ACLClassDTO have to be set.")
	private String aclClass;

	public ACLClassDTO(Long id, String aclClass) {
		super(id, aclClass);
		this.aclClass = aclClass;
	}

	public ACLClassDTO(ACLClass aclClass) {
		super(aclClass.getId(), aclClass.getAclClass());
		this.aclClass = aclClass.getAclClass();
	}

	public String getAclClass() {
		return aclClass;
	}

	public void setAclClass(String aclClass) {
		this.aclClass = aclClass;
	}

}
