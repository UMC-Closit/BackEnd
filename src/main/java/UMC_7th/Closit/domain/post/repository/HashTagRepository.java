package UMC_7th.Closit.domain.post.repository;

import UMC_7th.Closit.domain.post.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    Optional<HashTag> findByContent(String content);
}

