package UMC_7th.Closit.domain.user.repository;

import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
