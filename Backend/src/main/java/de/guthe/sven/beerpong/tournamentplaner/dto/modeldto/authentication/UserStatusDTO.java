package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;

public class UserStatusDTO extends EnumDTO {

    private UserStatusType userStatus;

    public UserStatusDTO(Long id, UserStatusType userStatus) {
        super(id, userStatus.name());
        this.userStatus = userStatus;
    }

    public UserStatusDTO(UserStatus userStatus) {
        super(userStatus.getId(), userStatus.getUserStatus().name());
        this.userStatus = userStatus.getUserStatus();
    }

    public UserStatusType getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusType userStatus) {
        this.userStatus = userStatus;
    }

}
