package de.guthe.sven.beerpong.tournamentplaner.repository.test;

import de.guthe.sven.beerpong.tournamentplaner.model.test.TestObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestObjectRepository extends JpaRepository<TestObject, Long> {

}