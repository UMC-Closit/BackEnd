package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.entity.Post;
import org.springframework.data.domain.Slice;

public interface HistoryQueryService {
    Slice<Post> getHistoryThumbnailList(Integer page); // 날짜 별 조회 - 썸네일
}
