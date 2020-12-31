package de.guthe.sven.beerpong.tournamentplaner.controller;

import de.guthe.sven.beerpong.tournamentplaner.model.test.TestObject;
import de.guthe.sven.beerpong.tournamentplaner.repository.test.TestObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private JdbcMutableAclService aclService;

    @Autowired
    private TestObjectRepository testObjectRepository;

    @GetMapping("/user")
    public String getLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;

    }

    @GetMapping("/setup")
    @Transactional
    public void setup() {

        TestObject testObject = new TestObject();
        testObject.setName("Testobject");

        testObjectRepository.save(testObject);

        ObjectIdentity oi = new ObjectIdentityImpl(TestObject.class, testObject.getId());
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Sid sid = new PrincipalSid(userDetails.getUsername());
        Permission permission = BasePermission.READ;

        // Create or update the relevant ACL
        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }

        // Now grant some permissions via an access control entry (ACE)
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        aclService.updateAcl(acl);

    }

    @GetMapping("/testobject")
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public TestObject getTestobject() {
        return testObjectRepository.findById(6L).get();
    }

}
