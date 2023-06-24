package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    User finbByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
