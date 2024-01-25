package com.maryj.authenticationsystem.service.impl;

import com.maryj.authenticationsystem.dto.LoginRequest;
import com.maryj.authenticationsystem.dto.Request;
import com.maryj.authenticationsystem.dto.Response;
import com.maryj.authenticationsystem.entity.User;
import com.maryj.authenticationsystem.repository.UserRepository;
import com.maryj.authenticationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public ResponseEntity<Response> signUp(Request request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Response.builder()
                    .statusCode(400)
                    .responseMessage("Attempt to save a duplicate")
                    .build());
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        User savedUser =userRepository.save(user);
        return ResponseEntity.ok(Response.builder().statusCode(200)
                .responseMessage("SUCCESS")
                .userInfo(modelMapper.map(savedUser, Request.class)).build());
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {
        return null;
    }

    @Override
    public Response sendOtp() {
        return null;
    }

    @Override
    public Response validateOtp() {
        return null;
    }

    @Override
    public Response resetPassword() {
        return null;
    }

    @Override
    public Response changePassword() {
        return null;
    }
}
