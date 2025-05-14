package org.drobysh.webrise.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.drobysh.webrise.dto.request.UserDto;
import org.drobysh.webrise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user-controller")
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping("/users")
    public ResponseEntity<String> createUser(UserDto request) {
        log.info("Creating user: {}", request);
        return null;
    }
}
