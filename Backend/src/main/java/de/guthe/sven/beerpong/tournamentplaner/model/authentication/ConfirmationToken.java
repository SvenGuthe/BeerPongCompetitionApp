package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
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

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable=false)
	@JsonIgnore
	private User user;

	public ConfirmationToken(User user) {
		this.user = user;
		createdDate = new Timestamp(System.currentTimeMillis());
		confirmationToken = UUID.randomUUID().toString();
	}

	public ConfirmationToken() {
	}

	public void setTokenid(Long tokenid) {
		this.tokenid = tokenid;
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
