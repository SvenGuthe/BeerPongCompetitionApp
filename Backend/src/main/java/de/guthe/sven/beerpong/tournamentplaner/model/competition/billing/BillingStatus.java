package de.guthe.sven.beerpong.tournamentplaner.model.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "billingstatus")
public class BillingStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billingstatusid")
	private Long id;

	@Column(name = "billingstatusdescription", nullable = false)
	@Enumerated(EnumType.STRING)
	private BillingStatusType billingStatusDescription;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "billingStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<BillingStatusHistory> billingStatusHistories;

	public BillingStatus() {
	}

	public BillingStatus(BillingStatusType billingStatusDescription) {
		this.billingStatusDescription = billingStatusDescription;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return BillingStatus.class;
	}

	public BillingStatusType getBillingStatusDescription() {
		return billingStatusDescription;
	}

	public void setBillingStatusDescription(BillingStatusType billingStatusDescription) {
		this.billingStatusDescription = billingStatusDescription;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<BillingStatusHistory> getBillingStatusHistories() {
		return billingStatusHistories;
	}

	public void setBillingStatusHistories(List<BillingStatusHistory> billingStatusHistories) {
		this.billingStatusHistories = billingStatusHistories;
	}

}
