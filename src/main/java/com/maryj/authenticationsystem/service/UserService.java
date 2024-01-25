package com.maryj.authenticationsystem.service;

import com.maryj.authenticationsystem.dto.LoginRequest;
import com.maryj.authenticationsystem.dto.Request;
import com.maryj.authenticationsystem.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<Response> signUp (Request request);
   ResponseEntity<Response> login(LoginRequest request);
    Response sendOtp();
    Response validateOtp();
    Response resetPassword();
    Response changePassword();

}
