package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Venison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenisonRepository extends JpaRepository<Venison, Long> {

    void deleteVenisonById(Long id);

    Page findByNameContaining(String filter, Pageable pageable);
}
