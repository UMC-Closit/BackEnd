package UMC_7th.Closit.domain.follow.repository;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User follower, User following);

    List<Follow> findByFollowing(User follower);

    // 특정 유저를 팔로우하는 사람 목록 조회 (팔로워)
    Slice<Follow> findByFollower(User user, Pageable pageable);

    // 특정 유저가 팔로우하는 사람 목록 조회 (팔로잉)
    Slice<Follow> findByFollowing(User user, Pageable pageable);
}
