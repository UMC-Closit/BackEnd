package UMC_7th.Closit.domain.post.converter;

import UMC_7th.Closit.domain.post.dto.LikeRequestDTO;
import UMC_7th.Closit.domain.post.dto.LikeResponseDTO;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;

public class LikeConverter {
    public static LikeResponseDTO.LikeStatusDTO toLikeStatusDTO(Post post, User user, boolean isLiked) {
        return LikeResponseDTO.LikeStatusDTO.builder()
                .postId(post.getId())
                .userId(user.getId())
                .isLiked(isLiked)
                .build();
    }
}
