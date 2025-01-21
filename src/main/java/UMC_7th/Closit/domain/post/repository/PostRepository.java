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
    @Query("SELECT p FROM Post p JOIN p.postHashTagList pht WHERE p.user IN :users AND pht.hashTag.id = :hashtagId")
    Slice<Post> findByUserInAndHashtagId(List<User> users, Long hashtagId, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.postHashTagList pht WHERE pht.hashTag.id = :hashtagId")
    Slice<Post> findByHashtagId(Long hashtagId, Pageable pageable);

}
