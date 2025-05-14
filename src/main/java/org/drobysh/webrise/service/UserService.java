package org.drobysh.webrise.service;

import org.drobysh.webrise.dto.request.UserCreateDto;
import org.drobysh.webrise.dto.response.SubscriptionDtoResponse;
import org.drobysh.webrise.dto.response.UserDtoResponse;

public interface UserService {

    UserDtoResponse createUser(UserCreateDto user);

    UserDtoResponse getUser(Long id);

    UserDtoResponse updateUser(Long id, UserCreateDto user);

    void deleteUser(Long id, UserCreateDto request);

    UserDtoResponse addSubscription(Long id, String subscription);

    SubscriptionDtoResponse getSubscription(Long id);

    boolean deleteSubscription(Long id, Long subId);

    SubscriptionDtoResponse topSubscription();
}
