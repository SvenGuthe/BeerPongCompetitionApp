package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authorization.ACLClassDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acl")
public class ACLClassController {

	private final ACLClassRepository aclClassRepository;

	@Autowired
	public ACLClassController(ACLClassRepository aclClassRepository) {
		this.aclClassRepository = aclClassRepository;
	}

	@PostMapping("/aclclass")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLClass addACLClass(@RequestBody ACLClass aclClass) {
		return aclClassRepository.save(aclClass);
	}

	@GetMapping("/aclclass")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getACLClasses(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		Page<ACLClass> pageRequest;
		if (search.equals("")) {
			pageRequest = aclClassRepository.findAll(PageRequest.of(page, size));
		}
		else {
			pageRequest = aclClassRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	@GetMapping("/aclclass/{aclClassId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLClassDTO getACLClass(@PathVariable Long aclClassId) {
		return new ACLClassDTO(aclClassRepository.findById(aclClassId).orElseThrow());
	}

}
