package com.gongkademy.domain.notification.controller;

import com.gongkademy.domain.member.dto.PrincipalDetails;
import com.gongkademy.domain.notification.service.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationServiceImpl notificationService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(
            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") final String lastEventId,
            @AuthenticationPrincipal final PrincipalDetails principalDetails
            ) {
        return ResponseEntity.ok(notificationService.connect(principalDetails.getMemberId(), lastEventId));
    }
}
