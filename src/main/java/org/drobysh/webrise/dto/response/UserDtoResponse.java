package org.drobysh.webrise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.drobysh.webrise.dto.request.SubscriptionDto;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDtoResponse {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private List<SubscriptionDto> subscriptions;
}
