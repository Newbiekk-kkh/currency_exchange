package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email을 찾을 수 없습니다."));
    }

    default User findByIdOrElseThrow(Long id) {
       return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID를 찾을 수가 없습니다."));
    }
}
