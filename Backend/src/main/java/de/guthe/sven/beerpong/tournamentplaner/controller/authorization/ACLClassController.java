package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authorization.ACLClassDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acl")
public class ACLClassController {

	private final ACLClassRepository aclClassRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclClassRepository jpa repository to handle all database queries directly in
	 * this controller regarding the acl classes
	 */
	@Autowired
	public ACLClassController(ACLClassRepository aclClassRepository) {
		this.aclClassRepository = aclClassRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(ACLClassController.class);

	/**
	 * Route to add an acl class TODO: This is a debugging route and should be removed
	 * later in production
	 * @param aclClass Because there have to be no Data Transfer Object, we are using the
	 * model-class of the ACL Class Controller
	 * @return the inserted ACL Class
	 */
	@PostMapping("/aclclass")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLClass addACLClass(@RequestBody ACLClass aclClass) {
		logger.info("Try to store: " + aclClass);
		return aclClassRepository.save(aclClass);
	}

	/**
	 * Route to return all ACL Classes with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the ACL Class
	 */
	@GetMapping("/aclclass")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getACLClasses(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of ACL Classes");

		Page<ACLClass> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM ACL CLASS
			pageRequest = aclClassRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM ACL Class WHERE ACL CLASS NAME LIKE search
			pageRequest = aclClassRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Enum Data Transfer Objects
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		logger.info("Return the following page: " + data);

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single ACL Class by the ID TODO: This is a debugging route and
	 * should be removed later in production
	 * @param aclClassId the id of the ACL Class in the database
	 * @return the ACL Class in a data transfer object
	 */
	@GetMapping("/aclclass/{aclClassId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLClassDTO getACLClass(@PathVariable Long aclClassId) {
		logger.info("Trying to find the ACL Class with the ID " + aclClassId);

		Optional<ACLClass> aclClass = aclClassRepository.findById(aclClassId);

		if (aclClass.isEmpty()) {
			throw new RuntimeException("ACL Class not present with given id " + aclClassId);
		}
		else {
			return new ACLClassDTO(aclClassRepository.findById(aclClassId).orElseThrow());
		}

	}

}
