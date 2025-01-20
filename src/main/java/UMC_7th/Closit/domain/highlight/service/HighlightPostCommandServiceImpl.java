package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.converter.HighlightPostConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightPostRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.entity.HighlightPost;
import UMC_7th.Closit.domain.highlight.repository.HighlightPostRepository;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HighlightPostCommandServiceImpl implements HighlightPostCommandService {

    private final HighlightPostRepository highlightPostRepository;
    //private final PostRepository postRepository;
    private final HighlightRepository highlightRepository;

    @Override
    @Transactional
    public HighlightPost createHighlightPost(HighlightPostRequestDTO.CreateHighlightPostDTO request) {
        //Post post = postRepository.findById(request.getPost());
        //Highlight highlight = highlightRepository.findById(request.getHighlight());
        //HighlightPost newHighlightPost = HighlightPostConverter.toHighlightPost(request, post, highlight);

        //return highlightPostRepository.save(newHighlightPost);
        return null;
    }
}
