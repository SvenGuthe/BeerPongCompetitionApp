package de.guthe.sven.beerpong.tournamentplaner.model.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "registrationstatus")
public class RegistrationStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registrationstatusid")
	private Long id;

	@Column(name = "registrationstatusdescription", nullable = false)
	@Enumerated(EnumType.STRING)
	private RegistrationStatusType registrationStatusDescription;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "registrationStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<RegistrationStatusHistory> registrationStatusHistories;

	public RegistrationStatus() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return RegistrationStatus.class;
	}

	public RegistrationStatusType getRegistrationStatusDescription() {
		return registrationStatusDescription;
	}

	public void setRegistrationStatusDescription(RegistrationStatusType registrationStatusDescription) {
		this.registrationStatusDescription = registrationStatusDescription;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<RegistrationStatusHistory> getRegistrationStatusHistories() {
		return registrationStatusHistories;
	}

	public void setRegistrationStatusHistories(List<RegistrationStatusHistory> registrationStatusHistories) {
		this.registrationStatusHistories = registrationStatusHistories;
	}

}
