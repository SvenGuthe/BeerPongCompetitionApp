package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQuery(name = "ConfirmationToken.findByConfirmationToken",
		query = "SELECT c FROM ConfirmationToken c WHERE LOWER(c.confirmationToken) = LOWER(?1)")
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tokenid")
	private Long tokenid;

	@Column(name = "confirmationtoken")
	private String confirmationToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userid")
	private User user;

	public ConfirmationToken(User user) {
		this.user = user;
		createdDate = new Date();
		confirmationToken = UUID.randomUUID().toString();
	}

	public ConfirmationToken() {
	}

	public Long getTokenid() {
		return tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
