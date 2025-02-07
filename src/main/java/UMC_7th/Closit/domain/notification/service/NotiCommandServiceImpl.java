package UMC_7th.Closit.domain.notification.service;

import UMC_7th.Closit.domain.notification.converter.NotificationConverter;
import UMC_7th.Closit.domain.notification.dto.NotificationRequestDTO;
import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.notification.repository.NotificationRepository;
import UMC_7th.Closit.domain.notification.repository.EmitterRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotiCommandServiceImpl implements NotiCommandService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Notification sendNotification (NotificationRequestDTO.SendNotiRequestDTO request) { // 알림 전송
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Notification notification = NotificationConverter.toNotification(user, request);

        return notificationRepository.save(notification);
    }
}