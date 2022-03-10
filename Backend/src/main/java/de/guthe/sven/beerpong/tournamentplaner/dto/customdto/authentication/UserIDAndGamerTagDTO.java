package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

public class UserIDAndGamerTagDTO {

	private Long id;

	private String gamerTag;

	public UserIDAndGamerTagDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGamerTag() {
		return gamerTag;
	}

	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	@Override
	public String toString() {
		return "UserIDAndGamerTagDTO{" + "userId=" + id + ", gamerTag='" + gamerTag + '\'' + '}';
	}

}
