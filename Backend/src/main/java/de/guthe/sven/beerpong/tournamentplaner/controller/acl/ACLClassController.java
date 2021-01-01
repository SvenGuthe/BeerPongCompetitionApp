package de.guthe.sven.beerpong.tournamentplaner.controller.acl;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLClassController {

    private ACLClassRepository aclClassRepository;

    @Autowired
    public ACLClassController(ACLClassRepository aclClassRepository) {
        this.aclClassRepository = aclClassRepository;
    }

    @GetMapping("/aclclass")
    @PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
    public List<ACLClass> getACLClasses() { return aclClassRepository.findAll(); }

}
