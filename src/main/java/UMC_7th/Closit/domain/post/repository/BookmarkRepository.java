package UMC_7th.Closit.domain.post.repository;

import UMC_7th.Closit.domain.post.entity.Bookmark;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByUserAndPost(User user, Post post);
}
