package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.dtos.TeamDto;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.TeamMapper;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public TeamDto createTeam(TeamDto team) throws ValidationException {
        Team created = teamRepository.save(teamMapper.dtoToEntity(team));
        return teamMapper.entityToDto(created);
    }

    public List<TeamDto> findAllTeams() {
        return teamRepository.findAll().stream().map(team -> teamMapper.entityToDto(team)).collect(Collectors.toList());
    }

    public TeamDto findTeamById(Long id) {
        Team team = teamRepository.findById(id).get();
        return teamMapper.entityToDto(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteTeamById(id);
    }

}
