package org.drobysh.webrise.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotFoundExceptionMessage {

    USER_NOT_FOUNT("Пользователь с таким идентификатором не найден"),
    SUBSCRIPTIONS_NOT_FOUND("Не найдено ни одной подписки");
    private final String message;
}
