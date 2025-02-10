package UMC_7th.Closit.domain.post.repository;

import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.entity.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {
    List<PostHashtag> findByPost(Post post);
}
