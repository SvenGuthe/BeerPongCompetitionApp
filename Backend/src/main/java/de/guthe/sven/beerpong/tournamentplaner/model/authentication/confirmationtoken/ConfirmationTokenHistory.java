package de.guthe.sven.beerpong.tournamentplaner.model.authentication.confirmationtoken;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.confirmationtoken.ConfirmationToken;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "confirmationtokenhistory")
public class ConfirmationTokenHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "confirmationtokenhistoryid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	@JsonIgnore
	private User user;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "confirmationtokenid", nullable = false)
	@JsonIgnore
	private ConfirmationToken confirmationToken;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	public ConfirmationTokenHistory(User user, ConfirmationToken confirmationToken, Timestamp validFrom) {
		this.user = user;
		this.confirmationToken = confirmationToken;
		this.validFrom = validFrom;
	}

	public ConfirmationTokenHistory(User user, ConfirmationToken confirmationToken) {
		this.user = user;
		this.confirmationToken = confirmationToken;
	}

	public ConfirmationTokenHistory() {
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ConfirmationToken getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(ConfirmationToken confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

}