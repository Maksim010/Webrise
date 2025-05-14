package org.drobysh.webrise.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IllegalArgumentExceptionMessage {

    SUBSCRIPTION_EXISTS("Эта подписка уже оформлена");
    private final String message;
}
