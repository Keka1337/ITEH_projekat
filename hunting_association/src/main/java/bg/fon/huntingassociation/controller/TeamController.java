package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.TeamMapper;
import bg.fon.huntingassociation.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/team")
public class TeamController {

    private final Logger LOG = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        try {
            return new ResponseEntity<>(teamMapper.entityToDto(teamService.createTeam(team)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTemas() {
        List<Team> teams = this.teamService.findAllTeams();
        return new ResponseEntity<>(teams.stream().map(team -> teamMapper.entityToDto(team)), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findTeamById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(teamMapper.entityToDto(teamService.findTeamById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id) {
        try {
            this.teamService.deleteTeam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
