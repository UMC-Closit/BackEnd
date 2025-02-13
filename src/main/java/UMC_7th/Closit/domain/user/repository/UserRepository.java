package UMC_7th.Closit.domain.user.repository;

import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByClositId (String clositId);

    Optional<User> findByEmail (String email);

    Optional<User> findById(Long id);

    Optional<User> findByName (String name);

    Optional<User> findByClositId (String clositId);
}
