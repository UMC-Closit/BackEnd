package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.PostRequestDTO;
import UMC_7th.Closit.domain.post.entity.Post;
import org.springframework.web.multipart.MultipartFile;

public interface PostCommandService {

    Post createPost(PostRequestDTO.CreatePostDTO request, MultipartFile frontImage, MultipartFile backImage);

    Post updatePost(Long postId, PostRequestDTO.UpdatePostDTO request);

    void deletePost(Long postId);
}

