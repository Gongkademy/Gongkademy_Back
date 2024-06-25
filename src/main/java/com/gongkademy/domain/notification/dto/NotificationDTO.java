package com.gongkademy.domain.notification.dto;

import com.gongkademy.domain.notification.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    //알람 목록조회
    private String receiver;
    private NotificationType type;
    private Long articleId;
    private String message;
    private boolean isRead;
    private LocalDateTime create_date;


}
