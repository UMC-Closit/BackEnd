package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;

import java.util.List;

public interface UserQueryService {

    List<Highlight> getHighlightList(Long userId);
}
