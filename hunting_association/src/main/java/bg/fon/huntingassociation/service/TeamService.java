package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.dtos.TeamDto;
import bg.fon.huntingassociation.mappers.TeamMapper;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
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

    public HashMap<String,Object> findAllPageable(int pageNumber, int pageSize, String sortBy, String filter) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page page;
        if(filter == null)
            page = this.teamRepository.findAll(pageable);
        else
            page = this.teamRepository.findByNameContaining(filter,pageable);

        List<Team> teams = page.getContent();
        List<TeamDto> dtos = teams.stream().map(team -> teamMapper.entityToDto(team)).collect(Collectors.toList());
        Long total = page.getTotalElements();

        HashMap<String,Object> map = new HashMap();
        map.put("total", total);
        map.put("content", dtos);
        return map;
    }

    public TeamDto editTeam(TeamDto teamDto) {
        Team team = this.teamRepository.save(teamMapper.dtoToEntity(teamDto));
        return teamMapper.entityToDto(team);
    }
}
