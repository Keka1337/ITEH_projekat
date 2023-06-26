package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.dtos.TeamDto;
import bg.fon.huntingassociation.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Transactional
@RestController
@RequestMapping("api/v1/team")
public class TeamController {

    private final Logger LOG = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody TeamDto team) {
        try {
            return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTemas() {
        return new ResponseEntity<>(teamService.findAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findTeamById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(teamService.findTeamById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-pageable")
    public ResponseEntity<?> getAllTeamsPageable(@RequestParam("pageNumber") int pageNumber,
                                                 @RequestParam("pageSize") int pageSize,
                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                 @RequestParam(required = false) String filter) {
        return new ResponseEntity<>(teamService.findAllPageable(pageNumber,pageSize, sortBy, filter), HttpStatus.OK);
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

    @PatchMapping("edit")
    public ResponseEntity<?> editHunter(@RequestBody TeamDto team) {
        try {
            return new ResponseEntity<>(teamService.editTeam(team), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
