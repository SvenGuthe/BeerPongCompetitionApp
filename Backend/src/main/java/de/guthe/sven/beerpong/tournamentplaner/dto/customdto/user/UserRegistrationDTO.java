package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user;

import javax.validation.constraints.NotNull;

public class UserRegistrationDTO {

	@NotNull(message = "firstName in UserRegistrationDTO have to be set.")
	private String firstName;

	@NotNull(message = "lastName in UserRegistrationDTO have to be set.")
	private String lastName;

	@NotNull(message = "gamerTag in UserRegistrationDTO have to be set.")
	private String gamerTag;

	@NotNull(message = "email in UserRegistrationDTO have to be set.")
	private String email;

	@NotNull(message = "password in UserRegistrationDTO have to be set.")
	private String password;

	public UserRegistrationDTO(String firstName, String lastName, String gamerTag, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gamerTag = gamerTag;
		this.email = email;
		this.password = password;
	}

	public UserRegistrationDTO() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGamerTag() {
		return gamerTag;
	}

	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
