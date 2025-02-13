package UMC_7th.Closit.domain.todaycloset.service;

import UMC_7th.Closit.domain.todaycloset.entity.TodayCloset;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TodayClosetQueryService {
    Slice<TodayCloset> getTodayClosetList(Integer page);
}


