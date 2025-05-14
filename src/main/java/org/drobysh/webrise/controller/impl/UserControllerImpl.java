package org.drobysh.webrise.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.drobysh.webrise.controller.UserController;
import org.drobysh.webrise.dto.request.UserCreateDto;
import org.drobysh.webrise.dto.response.SubscriptionDtoResponse;
import org.drobysh.webrise.dto.response.UserDtoResponse;
import org.drobysh.webrise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user-controller")
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping("/users")
    public ResponseEntity<UserDtoResponse> createUser(
            @RequestBody UserCreateDto request) {
        log.info("Creating user: {}", request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.createUser(request));
    }

    @Override
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDtoResponse> getUser(
            @PathVariable Long id) {
        log.info("Getting user: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUser(id));

    }

    @Override
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserCreateDto request) {
        log.info("Updating user: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(id, request));
    }

    @Override
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestBody UserCreateDto request) {
        log.info("Deleting user: {}", id);

        userService.deleteUser(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    @PostMapping("/users/{id}/subscriptions")
    public ResponseEntity<UserDtoResponse> addSubscription(
            @PathVariable Long id,
            @RequestParam String subscription) {
        log.info("Add subscription is started");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.addSubscription(id,subscription));
    }

    @Override
    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<SubscriptionDtoResponse> getSubscription(
            @PathVariable Long id) {
        log.info("Getting subscription for user: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getSubscription(id));
    }

    @Override
    @DeleteMapping("/users/{id}/subscriptions/{subId}")
    public ResponseEntity<Boolean> deleteSubscription(
            @PathVariable Long id,
            @PathVariable Long subId) {
        log.info("Deleting subscription for user: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.deleteSubscription(id, subId));
    }

    @Override
    @GetMapping("/subscriptions/top")
    public ResponseEntity<SubscriptionDtoResponse> topSubscription() {
        log.info("TopSubscription is started");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.topSubscription());
    }
}
