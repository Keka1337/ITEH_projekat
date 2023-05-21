package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HunterRepository extends JpaRepository<Hunter, Long> {

    Hunter findByJmbg(String jmbg);

    Hunter findByLicenceNum(String licenceNum);

}
