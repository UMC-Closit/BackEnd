package UMC_7th.Closit.domain.post.repository;

import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long postId);

    @Query("SELECT p FROM Post p WHERE p.user IN :users ORDER BY p.createdAt DESC")
    Slice<Post> findByUsers(List<User> users, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.postHashtagList pht WHERE p.user IN :users AND pht.hashtag.id = :hashtagId ORDER BY p.createdAt DESC")
    Slice<Post> findByUsersAndHashtagId(List<User> users, Long hashtagId, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.postHashtagList pht WHERE pht.hashtag.id = :hashtagId ORDER BY p.createdAt DESC")
    Slice<Post> findByHashtagId(Long hashtagId, Pageable pageable);

    Slice<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
