package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    @Query(value = "SELECT * FROM UserStatus us WHERE LOWER(us.userstatus) LIKE CONCAT(LOWER(?1), '%') OR us.id LIKE CONCAT(?1, '%')",
            countQuery = "SELECT count(*) FROM UserStatus us WHERE LOWER(us.userstatus) LIKE CONCAT(LOWER(?1), '%') OR us.id LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    Page<UserStatus> findAll(String search, PageRequest pageRequest);

    @Query("select us from UserStatus us")
    Page<UserStatus> findAll(PageRequest pageRequest);

}
