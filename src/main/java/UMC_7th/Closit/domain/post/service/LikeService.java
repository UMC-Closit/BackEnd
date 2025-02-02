package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.LikeRequestDTO;
import UMC_7th.Closit.domain.post.dto.LikeResponseDTO;

public interface LikeService {
    LikeResponseDTO.LikeStatusDTO likePost(LikeRequestDTO.CreateLikeDTO request);
    void unlikePost(LikeRequestDTO.UnlikeDTO request);
}
