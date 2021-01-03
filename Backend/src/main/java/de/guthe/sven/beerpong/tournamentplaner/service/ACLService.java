package de.guthe.sven.beerpong.tournamentplaner.service;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.*;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLEntryRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLObjectIdentityRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLSidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ACLService {

    ACLClassRepository aclClassRepository;

    ACLSidRepository aclSidRepository;

    ACLObjectIdentityRepository aclObjectIdentityRepository;

    ACLEntryRepository aclEntryRepository;

    UserRepository userRepository;

    @Autowired
    public ACLService(ACLClassRepository aclClassRepository, ACLSidRepository aclSidRepository, ACLObjectIdentityRepository aclObjectIdentityRepository, ACLEntryRepository aclEntryRepository, UserRepository userRepository) {
        this.aclClassRepository = aclClassRepository;
        this.aclSidRepository = aclSidRepository;
        this.aclObjectIdentityRepository = aclObjectIdentityRepository;
        this.aclEntryRepository = aclEntryRepository;
        this.userRepository = userRepository;
    }

    public List<Integer> getMasks(ACLObjectInterface aclObjectInterface) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByEmail(username);

        String className = aclObjectInterface.getACLClassName();
        ACLClass aclClass = aclClassRepository.findByClassName(className);

        if (aclClass == null) {
            return Collections.emptyList();
        } else {
            ACLSid aclSid = aclSidRepository.findByEmail(user.getEmail());
            if (aclSid == null) {
                return Collections.emptyList();
            } else {
                Long aclClassId = aclClass.getId();
                Long objectId = aclObjectInterface.getId();
                ACLObjectIdentity aclObjectIdentity = aclObjectIdentityRepository.findByACLClassObjectIdIdentity(aclClassId, objectId);
                if (aclObjectIdentity == null) {
                    return Collections.emptyList();
                } else {
                    return aclEntryRepository.findMasksByACLObjectIdentityACLSid(aclObjectIdentity.getId(), aclSid.getId());
                }
            }
        }
    }

}
