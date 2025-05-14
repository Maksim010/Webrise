package org.drobysh.webrise.service;

import lombok.RequiredArgsConstructor;
import org.drobysh.webrise.dto.request.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Override
    public String createUser(UserDto user) {


        return "";
    }
}
