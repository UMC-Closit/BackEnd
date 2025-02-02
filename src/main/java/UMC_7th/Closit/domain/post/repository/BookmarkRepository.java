package UMC_7th.Closit.domain.post.repository;

import UMC_7th.Closit.domain.post.entity.Bookmark;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByUserAndPost(User user, Post post);
    Optional<Bookmark> findByUserAndPost(User user, Post post);
    List<Bookmark> findByUser(User user);
}
