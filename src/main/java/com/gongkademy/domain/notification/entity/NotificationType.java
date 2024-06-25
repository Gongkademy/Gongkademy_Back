package com.gongkademy.domain.notification.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    Board("Board"),Notice("Notice");

    private final String key;
}
