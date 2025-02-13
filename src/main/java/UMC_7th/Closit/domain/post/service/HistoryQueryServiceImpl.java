package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryQueryServiceImpl implements HistoryQueryService{

    private final PostRepository postRepository;
    private final SecurityUtil securityUtil;

    @Override
    public Slice<Post> getHistoryThumbnailList (Integer page) { // 날짜 별 조회 - 썸네일
        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        Pageable pageable = PageRequest.of(page, 31);

        Slice<Post> postList = postRepository.findFrontImageByUserId(userId, pageable);

        return postList;
    }

    @Override
    public List<Post> getHistoryPreviewList(LocalDate localDate) { // 히스토리 게시글 상세 조회
        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        LocalDateTime start = localDate.atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        return postRepository.findAllByUserIdAndCreatedAtBetween(userId, start, end);
    }
}
