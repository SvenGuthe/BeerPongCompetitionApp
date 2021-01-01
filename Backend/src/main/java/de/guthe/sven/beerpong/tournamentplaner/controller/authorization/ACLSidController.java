package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLSid;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLSidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLSidController {

    private ACLSidRepository aclSidRepository;

    @Autowired
    public ACLSidController(ACLSidRepository aclSidRepository) {
        this.aclSidRepository = aclSidRepository;
    }

    @PostMapping("/aclsid")
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
    public ACLSid addACLSid(@RequestBody ACLSid aclSid) {
        return aclSidRepository.save(aclSid);
    }

    @GetMapping("/aclsid")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public List<ACLSid> getACLSids() { return aclSidRepository.findAll(); }

    @GetMapping("/aclsid/{aclSidId}")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public ACLSid getACLSid(@PathVariable Long aclSidId) {
        return aclSidRepository.findById(aclSidId).get();
    }

    @PutMapping("/aclsid")
    @PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
    public ACLSid updateACLSid(@RequestBody ACLSid aclSid) {
        return aclSidRepository.save(aclSid);
    }

}
