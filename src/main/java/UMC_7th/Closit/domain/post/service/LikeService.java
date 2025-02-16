package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.LikeRequestDTO;
import UMC_7th.Closit.domain.post.dto.LikeResponseDTO;

public interface LikeService {
    LikeResponseDTO.LikeStatusDTO likePost(LikeRequestDTO.CreateLikeDTO request);
    LikeResponseDTO.LikeStatusDTO unlikePost(LikeRequestDTO.UnlikeDTO request);
}
