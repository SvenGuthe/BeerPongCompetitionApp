package de.guthe.sven.beerpong.tournamentplaner.controller.acl;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLSid;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLSidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLSidController {

    private ACLSidRepository aclSidRepository;

    @Autowired
    public ACLSidController(ACLSidRepository aclSidRepository) {
        this.aclSidRepository = aclSidRepository;
    }

    @GetMapping("/aclsid")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public List<ACLSid> getACLSids() { return aclSidRepository.findAll(); }

}
