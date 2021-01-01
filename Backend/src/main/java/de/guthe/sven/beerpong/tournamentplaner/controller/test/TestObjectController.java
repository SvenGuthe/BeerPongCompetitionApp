package de.guthe.sven.beerpong.tournamentplaner.controller.test;

import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.model.test.TestObject;
import de.guthe.sven.beerpong.tournamentplaner.repository.test.TestObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestObjectController {

    private JdbcMutableAclService aclService;

    private TestObjectRepository testObjectRepository;

    @Autowired
    public TestObjectController(JdbcMutableAclService aclService, TestObjectRepository testObjectRepository) {
        this.aclService = aclService;
        this.testObjectRepository = testObjectRepository;
    }

    @PostMapping("/testobject")
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_TESTOBJECT_PRIVILEGE')")
    public TestObject addTestObject(@RequestBody TestObject testObject) {
        testObjectRepository.save(testObject);
        saveACL(testObject);
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
    @PreFilter("hasPermission(#testObject, 'WRITE') and hasAuthority('WRITE_TESTOBJECT_PRIVILEGE')")
    public TestObject updateTestObject ( @RequestBody TestObject testObject) {
        return testObjectRepository.save(testObject);
    }

    @DeleteMapping("/testobject")
    @Transactional
    @PreFilter("hasPermission(#testObject, 'WRITE') and hasAuthority('WRITE_TESTOBJECT_PRIVILEGE')")
    public void deleteTestObject(@RequestBody TestObject testObject) {
        testObjectRepository.delete(testObject);
    }

    private void saveACL(TestObject testObject) {

        ObjectIdentity oi = new ObjectIdentityImpl(TestObject.class, testObject.getId());

        // Create or update the relevant ACL
        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Sid sidCreator = new PrincipalSid(authentication);
        List<Permission> permissionsCreator = new LinkedList<>();
        permissionsCreator.add(BasePermission.READ);
        permissionsCreator.add(BasePermission.WRITE);
        permissionsCreator.add(BasePermission.CREATE);

        for (Permission permission : permissionsCreator) {
            acl.insertAce(acl.getEntries().size(), permission, sidCreator, true);
        }

        Sid administrator = new GrantedAuthoritySid(SecurityRole.ROLE_ADMINISTRATOR.toString());
        List<Permission> permissionsAdministrator = new LinkedList<>();
        permissionsAdministrator.add(BasePermission.READ);
        permissionsAdministrator.add(BasePermission.WRITE);
        permissionsAdministrator.add(BasePermission.CREATE);
        permissionsAdministrator.add(BasePermission.ADMINISTRATION);
        permissionsAdministrator.add(BasePermission.DELETE);

        for (Permission permission : permissionsAdministrator) {
            acl.insertAce(acl.getEntries().size(), permission, administrator, true);
        }

        Sid moderator = new GrantedAuthoritySid(SecurityRole.ROLE_MODERATOR.toString());
        List<Permission> permissionsModerator = new LinkedList<>();
        permissionsModerator.add(BasePermission.READ);
        permissionsModerator.add(BasePermission.WRITE);
        permissionsModerator.add(BasePermission.CREATE);
        permissionsModerator.add(BasePermission.DELETE);

        for (Permission permission : permissionsModerator) {
            acl.insertAce(acl.getEntries().size(), permission, moderator, true);
        }

        Sid player = new GrantedAuthoritySid(SecurityRole.ROLE_PLAYER.toString());
        List<Permission> permissionsPlayer = new LinkedList<>();
        permissionsPlayer.add(BasePermission.READ);

        for (Permission permission : permissionsPlayer) {
            acl.insertAce(acl.getEntries().size(), permission, player, true);
        }

        aclService.updateAcl(acl);

    }

}
