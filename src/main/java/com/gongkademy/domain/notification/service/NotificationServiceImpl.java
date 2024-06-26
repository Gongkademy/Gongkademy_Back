package com.gongkademy.domain.notification.service;

import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepository;
import com.gongkademy.domain.notification.entity.Notification;
import com.gongkademy.domain.notification.entity.NotificationType;
import com.gongkademy.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    @Override
    public Notification createNotification(Long memberId, NotificationType type, Long articleId, String message) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        Notification notification = Notification.builder()
                .receiver(member)
                .type(type)
                .articleId(articleId)
                .message(message)
                .isRead(false)
                .createTime(LocalDateTime.now())
                .build();

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotifications(Long memberId) {
        LocalDateTime now = LocalDateTime.now();

        //TODO: 임시로 3일전으로 함
        LocalDateTime threeDaysAgo = now.minusDays(3);

        return notificationRepository.findByReceiver_IdAndCreateTimeBetween(memberId, threeDaysAgo, now);
    }

    @Override
    public void changeReadStatus(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(IllegalArgumentException::new);

        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
