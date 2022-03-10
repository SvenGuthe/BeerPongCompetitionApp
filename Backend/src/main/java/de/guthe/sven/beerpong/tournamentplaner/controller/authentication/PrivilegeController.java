package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class PrivilegeController {

	private PrivilegeRepository privilegeRepository;

	@Autowired
	public PrivilegeController(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	@PostMapping("/privilege")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Privilege addPrivilege(@RequestBody Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	@GetMapping("/privilege/{privilegeId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public Privilege getPrivilege(@PathVariable Long privilegeId) {
		return privilegeRepository.findById(privilegeId).orElseThrow();
	}

	@GetMapping("/privilege")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getPrivileges(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<Privilege> pageRequest;
		if (search.equals("")) {
			pageRequest = privilegeRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = privilegeRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);
	}

}
