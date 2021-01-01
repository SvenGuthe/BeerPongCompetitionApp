package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectIdentity;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLObjectIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLObjectIdentityController {

    private ACLObjectIdentityRepository aclObjectIdentityRepository;

    @Autowired
    public ACLObjectIdentityController(ACLObjectIdentityRepository aclObjectIdentityRepository) {
        this.aclObjectIdentityRepository = aclObjectIdentityRepository;
    }

    @PostMapping("/aclobjectidentity")
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
    public ACLObjectIdentity addACLObjectIdentity(@RequestBody ACLObjectIdentity aclObjectIdentity) {
        return aclObjectIdentityRepository.save(aclObjectIdentity);
    }

    @GetMapping("/aclobjectidentity")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public List<ACLObjectIdentity> getACLObjectIdentities() { return aclObjectIdentityRepository.findAll(); }

    @GetMapping("/aclobjectidentity/{aclObjectIdentityId}")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public ACLObjectIdentity getACLObjectIdentity(@PathVariable Long aclObjectIdentityId) {
        return aclObjectIdentityRepository.findById(aclObjectIdentityId).get();
    }

    @PutMapping("/aclobjectidentity")
    @PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
    public ACLObjectIdentity updateACLObjectIdentity(@RequestBody ACLObjectIdentity aclObjectIdentity) {
        return aclObjectIdentityRepository.save(aclObjectIdentity);
    }

}
