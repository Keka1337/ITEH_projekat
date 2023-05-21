package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {

    void deleteTeamById(Long id);

}
