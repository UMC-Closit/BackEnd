package UMC_7th.Closit.global.s3;

import UMC_7th.Closit.global.s3.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
    Optional<Uuid> findByUuid(String uuid);
}
