package UMC_7th.Closit.domain.todaycloset.service;

import UMC_7th.Closit.domain.todaycloset.entity.TodayCloset;
import UMC_7th.Closit.domain.todaycloset.repository.TodayClosetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodayClosetQueryServiceImpl implements TodayClosetQueryService {

    private final TodayClosetRepository todayClosetRepository;

    @Override
    public Slice<TodayCloset> getTodayClosetList(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return todayClosetRepository.findAll(pageable);
    }
}
