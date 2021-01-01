package de.guthe.sven.beerpong.tournamentplaner.controller.acl;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLEntry;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLEntryController {

    private ACLEntryRepository aclEntryRepository;

    @Autowired
    public ACLEntryController(ACLEntryRepository aclEntryRepository) {
        this.aclEntryRepository = aclEntryRepository;
    }

    @GetMapping("/aclentry")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public List<ACLEntry> getACLEntries() {
        return aclEntryRepository.findAll();
    }

}
