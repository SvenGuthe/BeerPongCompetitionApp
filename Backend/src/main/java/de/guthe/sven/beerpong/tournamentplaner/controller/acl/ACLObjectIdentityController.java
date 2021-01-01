package de.guthe.sven.beerpong.tournamentplaner.controller.acl;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectIdentity;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLObjectIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLObjectIdentityController {

    private ACLObjectIdentityRepository aclObjectIdentityRepository;

    @Autowired
    public ACLObjectIdentityController(ACLObjectIdentityRepository aclObjectIdentityRepository) {
        this.aclObjectIdentityRepository = aclObjectIdentityRepository;
    }

    @GetMapping("/aclobjectidentity")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public List<ACLObjectIdentity> getACLObjectIdentities() { return aclObjectIdentityRepository.findAll(); }

}
