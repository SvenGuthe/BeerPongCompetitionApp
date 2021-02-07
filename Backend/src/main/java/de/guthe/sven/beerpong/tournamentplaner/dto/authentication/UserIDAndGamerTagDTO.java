package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

public class UserIDAndGamerTagDTO {

	private Long userId;

	private String gamerTag;

	public UserIDAndGamerTagDTO() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getGamerTag() {
		return gamerTag;
	}

	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	@Override
	public String toString() {
		return "UserIDAndGamerTagDTO{" + "userId=" + userId + ", gamerTag='" + gamerTag + '\'' + '}';
	}

}
