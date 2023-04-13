package com.example.hive.service;
import com.example.hive.dto.request.UserRegistrationRequestDto;
import com.example.hive.dto.response.UserRegistrationResponseDto;
import com.example.hive.entity.User;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import com.example.hive.entity.VerificationToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    Optional<User> findUserByEmail(String email);

    Optional<User> getUserByPasswordResetToken(String token);

    @Transactional
    UserRegistrationResponseDto registerUser(UserRegistrationRequestDto registrationRequestDto, HttpServletRequest request) throws IOException;

    Boolean validateRegistrationToken(String token);

    String generateVerificationToken(User user);
    VerificationToken generateNewToken(User user);

}
