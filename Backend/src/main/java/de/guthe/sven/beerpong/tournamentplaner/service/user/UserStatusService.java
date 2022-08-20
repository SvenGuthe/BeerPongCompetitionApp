package de.guthe.sven.beerpong.tournamentplaner.service.user;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.user.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStatusService {

	UserStatusRepository userStatusRepository;

	public UserStatusService(UserStatusRepository userStatusRepository) {
		this.userStatusRepository = userStatusRepository;
	}

	public UserStatus getOrCreateUserStatus(UserStatusType userStatusType) {
		Optional<UserStatus> userStatus = userStatusRepository.findByStatus(userStatusType.name());
		UserStatus finalUserStatus;
		if (userStatus.isEmpty()) {
			finalUserStatus = new UserStatus(userStatusType);
			userStatusRepository.save(finalUserStatus);
		}
		else {
			finalUserStatus = userStatus.get();
		}

		return finalUserStatus;
	}

}
