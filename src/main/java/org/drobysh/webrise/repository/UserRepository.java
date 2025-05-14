package org.drobysh.webrise.repository;

import org.drobysh.webrise.model.Subscription;
import org.drobysh.webrise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(Long id);

}
