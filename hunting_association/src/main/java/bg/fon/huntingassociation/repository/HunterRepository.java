package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Hunter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HunterRepository extends JpaRepository<Hunter, Long> {

    Hunter findByJmbg(String jmbg);

    Hunter findByLicenceNum(String licenceNum);

    Page findByNameContaining(String filter, Pageable pageable);

    boolean existsByJmbg(String jmbg);

    boolean existsByLicenceNum(String licenceNum);
}
