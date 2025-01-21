package UMC_7th.Closit.domain.highlight.repository;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HighlightRepository extends JpaRepository<Highlight, Long> {

    @Query("SELECT h FROM Highlight h LEFT JOIN FETCH h.highlightPosts hp WHERE h.id = :highlightId")
    Optional<Highlight> findByIdWithPosts(Long highlightId);

    List<Highlight> findAllByUser(User user);
}
