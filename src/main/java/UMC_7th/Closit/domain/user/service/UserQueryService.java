package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserQueryService {

    Slice<Highlight> getHighlightList(Long userId);
}
