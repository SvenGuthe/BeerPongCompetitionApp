package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.team.TeamOverviewDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

	private TeamRepository teamRepository;

	private ModelMapper modelMapper;

	@Autowired
	public TeamService(TeamRepository teamRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.teamRepository = teamRepository;
	}

	public List<TeamOverviewDTO> getActiveTeams(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Team> teams = teamRepository.findAllActiveTeams(pageRequest);
		return teams.getContent().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private TeamOverviewDTO convertToDto(Team team) {
		return modelMapper.map(team, TeamOverviewDTO.class);
	}

}
