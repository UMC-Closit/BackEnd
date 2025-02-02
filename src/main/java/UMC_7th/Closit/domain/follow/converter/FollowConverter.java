package UMC_7th.Closit.domain.follow.converter;

import UMC_7th.Closit.domain.follow.dto.FollowRequestDTO;
import UMC_7th.Closit.domain.follow.dto.FollowResponseDTO;
import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.user.entity.User;

import java.time.LocalDateTime;

public class FollowConverter {

    public static FollowResponseDTO.CreateFollowResultDTO toCreateFollowResultDTO(Follow follow) {
        return FollowResponseDTO.CreateFollowResultDTO.builder()
                .followId(follow.getId())
                .followerId(follow.getFollower().getId())
                .followingId(follow.getFollowing().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static FollowResponseDTO.FollowDTO toFollowDTO(Follow follow) {
        return FollowResponseDTO.FollowDTO.builder()
                .followId(follow.getId())
                .followerId(follow.getFollower().getId())
                .followingId(follow.getFollowing().getId())
                .createdAt(follow.getCreatedAt())
                .updatedAt(follow.getUpdatedAt())
                .build();
    }

    public static Follow toFollow(FollowRequestDTO.CreateFollowDTO request, User follwer, User following) {
        return Follow.builder()
                .follower(follwer)
                .following(following)
                .build();
    }
}
