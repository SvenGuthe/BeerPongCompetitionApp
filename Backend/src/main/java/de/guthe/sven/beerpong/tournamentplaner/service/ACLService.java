package de.guthe.sven.beerpong.tournamentplaner.service;

import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLEntryRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLObjectIdentityRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLSidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
