package org.drobysh.webrise.service.impl;

import lombok.RequiredArgsConstructor;
import org.drobysh.webrise.dto.request.SubscriptionDto;
import org.drobysh.webrise.dto.request.UserCreateDto;
import org.drobysh.webrise.dto.response.SubscriptionDtoResponse;
import org.drobysh.webrise.dto.response.UserDtoResponse;
import org.drobysh.webrise.enums.IllegalArgumentExceptionMessage;
import org.drobysh.webrise.enums.NotFoundExceptionMessage;
import org.drobysh.webrise.exceptions.IllegalArgumentException;
import org.drobysh.webrise.exceptions.NotFoundException;
import org.drobysh.webrise.model.Subscription;
import org.drobysh.webrise.model.User;
import org.drobysh.webrise.repository.SubscriptionRepository;
import org.drobysh.webrise.repository.UserRepository;
import org.drobysh.webrise.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public UserDtoResponse createUser(UserCreateDto requestDto) {
        User user = mapToUser(requestDto);
        userRepository.save(user);
        return mapToUserDtoResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoResponse getUser(Long id) {
        User user = findUserById(id);
        return mapToUserDtoResponse(user);
    }

    @Override
    @Transactional
    public UserDtoResponse updateUser(Long id, UserCreateDto requestDto) {
        User user = findUserById(id);
        user.setName(requestDto.getName());
        user.setDateOfBirth(requestDto.getDateOfBirth());
        user.setPassword(requestDto.getPassword());
        userRepository.save(user);
        return mapToUserDtoResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id, UserCreateDto request) {
        userRepository.delete(findUserById(id));
    }

    @Override
    @Transactional
    public UserDtoResponse addSubscription(Long id, String subscription) {
        User user = findUserById(id);

        boolean subscriptionExists = user.getSubscriptions().stream()
                .anyMatch(s -> s.getName().equals(subscription));

        if (subscriptionExists) {
            throw new IllegalArgumentException(IllegalArgumentExceptionMessage.SUBSCRIPTION_EXISTS.getMessage());
        }

        Subscription newSubscription = Subscription.builder()
                .name(subscription)
                .user(user)
                .build();
        user.getSubscriptions().add(newSubscription);

        userRepository.save(user);
        return mapToUserDtoResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionDtoResponse getSubscription(Long id) {
        List<Subscription> subscriptions = subscriptionRepository.findSubscriptionByUserId(id);
        return mapToSubscriptionDtoResponse(subscriptions);

    }

    @Override
    @Transactional
    public boolean deleteSubscription(Long id, Long subId) {
        Optional<Subscription> subscription = subscriptionRepository
                .findByUserIdAndId(id, subId);

        subscription.ifPresent(sub -> {
            User user = sub.getUser();
            user.getSubscriptions().remove(sub);
        });

        return subscription.isPresent();
    }

    @Transactional(readOnly = true)
    public SubscriptionDtoResponse topSubscription() {
        Page<Object[]> result = subscriptionRepository.findTop3PopularSubscriptions(
                PageRequest.of(0, 3));

        List<Subscription> dtos = result.getContent().stream()
                .map(arr -> Subscription.builder()
                        .name((String) arr[0])
                        .build())
                .toList();
        return mapToSubscriptionDtoResponse(dtos);
    }

    private static SubscriptionDto convertToSubscriptionDto(Subscription subscription) {
        return SubscriptionDto.builder()
                .subscriptionName(subscription.getName())
                .build();
    }

    public static SubscriptionDtoResponse mapToSubscriptionDtoResponse(List<Subscription> subscriptions) {
        List<SubscriptionDto> dtos = subscriptions.stream()
                .map(UserServiceImpl::convertToSubscriptionDto)
                .toList();

        return SubscriptionDtoResponse.builder()
                .subscriptions(dtos)
                .build();
    }


    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(
                () -> new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUNT.getMessage())
        );
    }

    public static User mapToUser(UserCreateDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .dateOfBirth(requestDto.getDateOfBirth())
                .password(requestDto.getPassword())
                .build();
    }

    public static UserDtoResponse mapToUserDtoResponse(User user) {
        UserDtoResponse userDtoResponse = UserDtoResponse.builder()
                .id(user.getId())
                .dateOfBirth(user.getDateOfBirth())
                .name(user.getName())
                .subscriptions(Collections.emptyList())
                .build();

        if(user.getSubscriptions() != null) {
            List<SubscriptionDto> subscriptionDto = user.getSubscriptions()
                    .stream()
                    .map(subDto -> SubscriptionDto.builder()
                            .subscriptionName(subDto.getName())
                            .build())
                    .toList();

            userDtoResponse.setSubscriptions(subscriptionDto);
        }

        return userDtoResponse;
    }
}
