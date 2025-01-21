package UMC_7th.Closit.domain.post.repository;

import UMC_7th.Closit.domain.post.entity.ItemTag;
import UMC_7th.Closit.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTagRepository extends JpaRepository<ItemTag, Long> {
    List<ItemTag> findByPostAndTagType(Post post, String tagType);
}
