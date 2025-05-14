package org.drobysh.webrise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.drobysh.webrise.dto.request.SubscriptionDto;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {

    private String name;

    private String password;

    private List<SubscriptionDto> subscriptions;
}

