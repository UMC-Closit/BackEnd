package UMC_7th.Closit.domain.notification.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Repository
public interface EmitterRepository {
    SseEmitter save(String emitterId, SseEmitter sseEmitter); // SseEmitter 저장
    void deleteById(String emitterId); // SseEmitter 삭제
    Map<String, SseEmitter> findAllEmitterByUserId(String userId);
    Map<String, Object> findAllEventCacheByUserId(String userId);
}