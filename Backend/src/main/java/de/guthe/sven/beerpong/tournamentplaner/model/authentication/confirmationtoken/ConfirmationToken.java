package de.guthe.sven.beerpong.tournamentplaner.model.authentication.confirmationtoken;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NamedQuery(name = "ConfirmationToken.findByConfirmationToken",
		query = "SELECT c FROM ConfirmationToken c WHERE LOWER(c.confirmationToken) = LOWER(?1)")
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tokenid")
	private Long id;

	@Column(name = "confirmationtoken", nullable = false)
	private String confirmationToken;

	@OneToMany(mappedBy = "confirmationToken", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private List<ConfirmationTokenHistory> confirmationTokenHistory;

	public ConfirmationToken() {
		this.confirmationToken = UUID.randomUUID().toString();
	}

	public ConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Long getId() {
		return id;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public List<ConfirmationTokenHistory> getConfirmationTokenHistory() {
		return confirmationTokenHistory;
	}

	public void setConfirmationTokenHistory(List<ConfirmationTokenHistory> confirmationTokenHistory) {
		this.confirmationTokenHistory = confirmationTokenHistory;
	}

}
