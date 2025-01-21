package UMC_7th.Closit.domain.follow.repository;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User follower, User following);
    List<Follow> findByFollower(User follower);

}
