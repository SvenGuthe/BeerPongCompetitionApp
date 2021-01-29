package de.guthe.sven.beerpong.tournamentplaner.controller.test;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamPermissions;
import de.guthe.sven.beerpong.tournamentplaner.model.test.TestObject;
import de.guthe.sven.beerpong.tournamentplaner.repository.test.TestObjectRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestObjectController {

	private ACLService aclService;

	private TestObjectRepository testObjectRepository;

	@Autowired
	public TestObjectController(ACLService aclService, TestObjectRepository testObjectRepository) {
		this.aclService = aclService;
		this.testObjectRepository = testObjectRepository;
	}

	@PostMapping("/testobject")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_TESTOBJECT_PRIVILEGE')")
	public TestObject addTestObject(@RequestBody TestObject testObject) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);
		List<Permission> permissionsCreator = new LinkedList<>();
		permissionsCreator.add(BasePermission.READ);
		permissionsCreator.add(BasePermission.WRITE);
		permissionsCreator.add(BasePermission.CREATE);

		Map<Sid, List<Permission>> initialTeamPermissions = TeamPermissions.initialTeamPermissions;
		initialTeamPermissions.put(sidCreator, permissionsCreator);

		testObjectRepository.save(testObject);
		aclService.setPrivileges(testObject, initialTeamPermissions);
		return testObject;
	}

	@GetMapping("/testobject/{testObjectId}")
	@PostAuthorize("hasPermission(returnObject, 'READ') and hasAuthority('READ_TESTOBJECT_PRIVILEGE')")
	public TestObject getTestObject(@PathVariable Long testObjectId) {
		return testObjectRepository.findById(testObjectId).get();
	}

	@GetMapping("/testobject")
	@PostFilter("hasPermission(filterObject, 'READ') and hasAuthority('READ_TESTOBJECT_PRIVILEGE')")
	public List<TestObject> getAllTestObjects() {
		return testObjectRepository.findAll();
	}

	@PutMapping("/testobject")
	@PreAuthorize("hasPermission(#testObject, 'WRITE') and hasAuthority('WRITE_TESTOBJECT_PRIVILEGE')")
	public TestObject updateTestObject(@RequestBody TestObject testObject) {
		return testObjectRepository.save(testObject);
	}

	@DeleteMapping("/testobject")
	@Transactional
	@PreAuthorize("hasPermission(#testObject, 'WRITE') and hasAuthority('WRITE_TESTOBJECT_PRIVILEGE')")
	public void deleteTestObject(@RequestBody TestObject testObject) {
		testObjectRepository.delete(testObject);
	}

}
