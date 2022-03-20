package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.billing.BillingStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.registration.RegistrationStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

    CompetitionRepository competitionRepository;

    CompetitionStatusRepository competitionStatusRepository;

    CompetitionAdminRepository competitionAdminRepository;

    CompetitionAdminStatusRepository competitionAdminStatusRepository;

    CompetitionTeamRepository competitionTeamRepository;

    RegistrationStatusRepository registrationStatusRepository;

    BillingStatusRepository billingStatusRepository;

    CompetitionPlayerRepository competitionPlayerRepository;

    CompetitionPlayerStatusRepository competitionPlayerStatusRepository;

    UserRepository userRepository;

    TeamRepository teamRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository,
                              UserRepository userRepository,
                              TeamRepository teamRepository,
                              CompetitionStatusRepository competitionStatusRepository,
                              CompetitionAdminRepository competitionAdminRepository,
                              CompetitionAdminStatusRepository competitionAdminStatusRepository,
                              CompetitionTeamRepository competitionTeamRepository,
                              RegistrationStatusRepository registrationStatusRepository,
                              BillingStatusRepository billingStatusRepository,
                              CompetitionPlayerRepository competitionPlayerRepository,
                              CompetitionPlayerStatusRepository competitionPlayerStatusRepository) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.competitionStatusRepository = competitionStatusRepository;
        this.competitionAdminRepository = competitionAdminRepository;
        this.competitionAdminStatusRepository = competitionAdminStatusRepository;
        this.competitionTeamRepository = competitionTeamRepository;
        this.registrationStatusRepository = registrationStatusRepository;
        this.billingStatusRepository = billingStatusRepository;
        this.competitionPlayerRepository = competitionPlayerRepository;
        this.competitionPlayerStatusRepository = competitionPlayerStatusRepository;
    }

    public CompetitionDetailDTO getCompetitionDetail(Long competitionId) {
        CompetitionDTO competition = new CompetitionDTO(competitionRepository.findById(competitionId).get());

        Collection<UserIDAndGamerTagDTO> possibleAdminUsers = userRepository.findAllPossibleAdmins(competitionId).stream().map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());
        Collection<UserIDAndGamerTagDTO> possiblePlayers = userRepository.findAllPossiblePlayers(competitionId).stream().map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());
        Collection<TeamAndUserDTO> teamAndUser = teamRepository.findAll().stream().map(TeamAndUserDTO::new).collect(Collectors.toList());

        return new CompetitionDetailDTO(competition, possibleAdminUsers, possiblePlayers, teamAndUser);
    }

    public List<CompetitionStatusDTO> updateCompetitionStatus(CompetitionStatusUpdateDTO competitionStatusUpdateDTO) {
        Competition competition = competitionRepository.findById(competitionStatusUpdateDTO.getId()).get();
        List<CompetitionStatus> allCompetitionStatus = competitionStatusRepository.findByStatus(competitionStatusUpdateDTO.getCompetitionStatusType().name());
        CompetitionStatus competitionStatus;

        if (allCompetitionStatus.size() == 0) {
            competitionStatus = new CompetitionStatus(competitionStatusUpdateDTO.getCompetitionStatusType());
        } else {
            competitionStatus = allCompetitionStatus.get(0);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<CompetitionStatusHistory> currentCompetitionStatusHistory = competition.getCompetitionStatusHistories().stream()
                .peek(competitionStatusHistory -> {
                    if (competitionStatusHistory.getValidTo() == null) {
                        competitionStatusHistory.setValidTo(now);
                    }
                }).collect(Collectors.toList());

        currentCompetitionStatusHistory.add(new CompetitionStatusHistory(
                now,
                competition,
                competitionStatus
        ));

        competition.setCompetitionStatusHistories(currentCompetitionStatusHistory);
        competitionRepository.save(competition);

        return currentCompetitionStatusHistory.subList(currentCompetitionStatusHistory.size()-2, currentCompetitionStatusHistory.size()).stream().map(CompetitionStatusDTO::new).collect(Collectors.toList());

    }

    public List<CompetitionAdminStatusDTO> updateCompetitionAdminStatus(CompetitionAdminStatusUpdateDTO competitionAdminStatusUpdateDTO) {
        CompetitionAdmin competitionAdmin = competitionAdminRepository.findById(competitionAdminStatusUpdateDTO.getId()).get();
        List<CompetitionAdminStatus> allCompetitionAdminStatus = competitionAdminStatusRepository.findByStatus(competitionAdminStatusUpdateDTO.getCompetitionAdminStatusType().name());

        CompetitionAdminStatus competitionAdminStatus;

        if (allCompetitionAdminStatus.size() == 0)  {
            competitionAdminStatus = new CompetitionAdminStatus(competitionAdminStatusUpdateDTO.getCompetitionAdminStatusType());
        } else {
            competitionAdminStatus = allCompetitionAdminStatus.get(0);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<CompetitionAdminStatusHistory> currentCompetitionAdminStatusHistory = competitionAdmin.getCompetitionAdminStatusHistories().stream()
                .peek(competitionAdminStatusHistory -> {
                    if (competitionAdminStatusHistory.getValidTo() == null) {
                        competitionAdminStatusHistory.setValidTo(now);
                    }
                }).collect(Collectors.toList());

        currentCompetitionAdminStatusHistory.add(new CompetitionAdminStatusHistory(
                now,
                competitionAdmin,
                competitionAdminStatus
        ));

        competitionAdmin.setCompetitionAdminStatusHistories(currentCompetitionAdminStatusHistory);
        competitionAdminRepository.save(competitionAdmin);

        return currentCompetitionAdminStatusHistory.subList(currentCompetitionAdminStatusHistory.size()-2, currentCompetitionAdminStatusHistory.size())
                .stream().map(CompetitionAdminStatusDTO::new).collect(Collectors.toList());

    }

    public List<RegistrationStatusDTO> updateRegistrationStatus(RegistrationStatusUpdateDTO registrationStatusUpdateDTO) {
        CompetitionTeam competitionTeam = competitionTeamRepository.findById(registrationStatusUpdateDTO.getId()).get();
        List<RegistrationStatus> allRegistrationStatus = registrationStatusRepository.findByStatus(registrationStatusUpdateDTO.getRegistrationStatusType().name());

        RegistrationStatus registrationStatus;

        if (allRegistrationStatus.size() == 0) {
            registrationStatus = new RegistrationStatus(registrationStatusUpdateDTO.getRegistrationStatusType());
        } else {
            registrationStatus = allRegistrationStatus.get(0);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<RegistrationStatusHistory> currentRegistrationStatusHistory = competitionTeam.getRegistrationStatusHistories().stream()
                .peek(registrationStatusHistory -> {
                    if (registrationStatusHistory.getValidTo() == null) {
                        registrationStatusHistory.setValidTo(now);
                    }
                }).collect(Collectors.toList());

        currentRegistrationStatusHistory.add(new RegistrationStatusHistory(
                now,
                competitionTeam,
                registrationStatus
        ));

        registrationStatus.setRegistrationStatusHistories(currentRegistrationStatusHistory);
        registrationStatusRepository.save(registrationStatus);

        return currentRegistrationStatusHistory.subList(currentRegistrationStatusHistory.size()-2, currentRegistrationStatusHistory.size())
                .stream().map(RegistrationStatusDTO::new).collect(Collectors.toList());

    }

    public List<BillingStatusDTO> updateBillingStatus(BillingStatusUpdateDTO billingStatusUpdateDTO) {
        CompetitionTeam competitionTeam = competitionTeamRepository.findById(billingStatusUpdateDTO.getId()).get();
        List<BillingStatus> allBillingStatus = billingStatusRepository.findByStatus(billingStatusUpdateDTO.getBillingStatusType().name());

        BillingStatus billingStatus;

        if (allBillingStatus.size() == 0) {
            billingStatus = new BillingStatus(billingStatusUpdateDTO.getBillingStatusType());
        } else {
            billingStatus = allBillingStatus.get(0);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<BillingStatusHistory> currentBillingStatusHistory = competitionTeam.getBillingStatusHistories().stream()
                .peek(billingStatusHistory -> {
                    if (billingStatusHistory.getValidTo() == null) {
                        billingStatusHistory.setValidTo(now);
                    }
                }).collect(Collectors.toList());

        currentBillingStatusHistory.add(new BillingStatusHistory(
                now,
                competitionTeam,
                billingStatus
        ));

        billingStatus.setBillingStatusHistories(currentBillingStatusHistory);
        billingStatusRepository.save(billingStatus);

        return currentBillingStatusHistory.subList(currentBillingStatusHistory.size()-2, currentBillingStatusHistory.size())
                .stream().map(BillingStatusDTO::new).collect(Collectors.toList());
    }

    public CompetitionAdminDTO addCompetitionAdmin(CompetitionAdminAddDTO competitionAdminAddDTO) {
        Competition competition = competitionRepository.findById(competitionAdminAddDTO.id).get();
        User user = userRepository.findById(competitionAdminAddDTO.getUserId()).get();

        List<CompetitionAdminStatus> competitionAdminStatusList = competitionAdminStatusRepository.findByStatus(CompetitionAdminStatusType.PROMISED.name());
        CompetitionAdminStatus competitionAdminStatus;

        if (competitionAdminStatusList.size() == 0) {
            competitionAdminStatus = new CompetitionAdminStatus(
                CompetitionAdminStatusType.PROMISED
            );
        } else {
            competitionAdminStatus = competitionAdminStatusList.get(0);
        }

        CompetitionAdmin competitionAdmin = new CompetitionAdmin();
        competitionAdmin.setUser(user);
        competitionAdmin.setCompetition(competition);
        competitionAdmin.addCompetitionAdminStatus(competitionAdminStatus);

        competitionAdminRepository.save(competitionAdmin);

        return new CompetitionAdminDTO(competitionAdmin);
    }

    public CompetitionPlayerDTO addCompetitionPlayer(CompetitionPlayerAddDTO competitionPlayerAddDTO) {
        CompetitionTeam competitionTeam = competitionTeamRepository.findById(competitionPlayerAddDTO.getId()).get();
        User user = userRepository.findById(competitionPlayerAddDTO.getUserId()).get();

        List<CompetitionPlayerStatus> competitionPlayerStatusList = competitionPlayerStatusRepository.findByStatus(CompetitionPlayerStatusType.PROMISED.name());
        CompetitionPlayerStatus competitionPlayerStatus;

        if (competitionPlayerStatusList.size() == 0) {
            competitionPlayerStatus = new CompetitionPlayerStatus(
                    CompetitionPlayerStatusType.PROMISED
            );
        } else {
            competitionPlayerStatus = competitionPlayerStatusList.get(0);
        }

        CompetitionPlayer competitionPlayer = new CompetitionPlayer();
        competitionPlayer.setCompetitionTeam(competitionTeam);
        competitionPlayer.setCompetitionPlayerStatus(competitionPlayerStatus);
        competitionPlayer.setUser(user);

        competitionPlayerRepository.save(competitionPlayer);

        return new CompetitionPlayerDTO(competitionPlayer);
    }

    public CompetitionTeamDTO addCompetitionTeam(CompetitionTeamAddDTO competitionTeamAddDTO) {
        Competition competition = competitionRepository.findById(competitionTeamAddDTO.getId()).get();

        Team team = null;
        if (competitionTeamAddDTO.getTeamId() != null) {
            team = teamRepository.findById(competitionTeamAddDTO.getTeamId()).get();
        }

        List<BillingStatus> billingStatusList = billingStatusRepository.findByStatus(BillingStatusType.NOT_PAYED.name());
        BillingStatus billingStatus;

        if (billingStatusList.size() == 0) {
            billingStatus = new BillingStatus(
                    BillingStatusType.NOT_PAYED
            );
        } else {
            billingStatus = billingStatusList.get(0);
        }

        List<RegistrationStatus> registrationStatusList = registrationStatusRepository.findByStatus(RegistrationStatusType.REGISTERED.name());
        RegistrationStatus registrationStatus;

        if (registrationStatusList.size() == 0) {
            registrationStatus = new RegistrationStatus(
                    RegistrationStatusType.REGISTERED
            );
        } else {
            registrationStatus = registrationStatusList.get(0);
        }

        CompetitionTeam competitionTeam = new CompetitionTeam();
        competitionTeam.setCompetition(competition);
        competitionTeam.setCompetitionTeamName(competitionTeamAddDTO.getTeamname());
        competitionTeam.setPassword(competitionTeamAddDTO.getPassword());
        competitionTeam.setTeam(team);
        competitionTeam.addBillingStatus(billingStatus);
        competitionTeam.addRegistrationStatus(registrationStatus);

        List<CompetitionPlayer> competitionPlayers = Arrays.stream(competitionTeamAddDTO.getPlayerIds()).map(playerId -> {
            User user = userRepository.findById(playerId).get();

            List<CompetitionPlayerStatus> competitionPlayerStatusList = competitionPlayerStatusRepository.findByStatus(CompetitionPlayerStatusType.PROMISED.name());
            CompetitionPlayerStatus competitionPlayerStatus;

            if (competitionPlayerStatusList.size() == 0) {
                competitionPlayerStatus = new CompetitionPlayerStatus(
                        CompetitionPlayerStatusType.PROMISED
                );
            } else {
                competitionPlayerStatus = competitionPlayerStatusList.get(0);
            }

            return new CompetitionPlayer(
                    competitionTeam,
                    user,
                    competitionPlayerStatus
            );

        }).collect(Collectors.toList());

        competitionTeam.setCompetitionPlayers(competitionPlayers);

        competitionTeamRepository.save(competitionTeam);

        return new CompetitionTeamDTO(competitionTeam);
    }

    public CompetitionDTO updateCompetition(CompetitionUpdateDTO competitionUpdateDTO) {
        Competition competition = competitionRepository.findById(competitionUpdateDTO.getId()).get();
        competition.setCompetitionName(competitionUpdateDTO.getCompetitionName());
        competition.setCompetitionStartTimestamp(competitionUpdateDTO.getCompetitionStartTimestamp());
        competition.setMinTeams(competitionUpdateDTO.getMinTeams());
        competition.setMaxTeams(competitionUpdateDTO.getMaxTeams());
        competition.setFee(competitionUpdateDTO.getFee());
        competition.setRegistrationStart(competitionUpdateDTO.getRegistrationStart());
        competition.setRegistrationEnd(competitionUpdateDTO.getRegistrationEnd());
        competition.setSetOfRules(competitionUpdateDTO.getSetOfRules());

        competitionRepository.save(competition);

        return new CompetitionDTO(competition);
    }

    public CompetitionPlayerStatusDTO updateCompetitionPlayerStatus(CompetitionPlayerStatusUpdateDTO competitionPlayerStatusUpdateDTO) {
        CompetitionPlayer competitionPlayer = competitionPlayerRepository.findById(competitionPlayerStatusUpdateDTO.getId()).get();

        List<CompetitionPlayerStatus> competitionPlayerStatusList = competitionPlayerStatusRepository.findByStatus(competitionPlayerStatusUpdateDTO.getCompetitionPlayerStatusType().name());
        CompetitionPlayerStatus competitionPlayerStatus;

        if (competitionPlayerStatusList.size() == 0) {
            competitionPlayerStatus = new CompetitionPlayerStatus(
                    competitionPlayerStatusUpdateDTO.getCompetitionPlayerStatusType()
            );
        } else {
            competitionPlayerStatus = competitionPlayerStatusList.get(0);
        }

        competitionPlayer.setCompetitionPlayerStatus(competitionPlayerStatus);

        competitionPlayerRepository.save(competitionPlayer);

        return new CompetitionPlayerStatusDTO(competitionPlayerStatus);
    }

    public CompetitionTeamDTO updateCompetitionTeam(CompetitionTeamUpdateDTO competitionTeamUpdateDTO) {
        CompetitionTeam competitionTeam = competitionTeamRepository.findById(competitionTeamUpdateDTO.getId()).get();
        if (competitionTeamUpdateDTO.getTeamId() != null) {
            Team team = teamRepository.findById(competitionTeamUpdateDTO.getTeamId()).get();
            competitionTeam.setTeam(team);
        } else {
            competitionTeam.setTeam(null);
        }
        competitionTeam.setCompetitionTeamName(competitionTeamUpdateDTO.getTeamname());

        competitionTeamRepository.save(competitionTeam);

        return new CompetitionTeamDTO(competitionTeam);
    }

}
