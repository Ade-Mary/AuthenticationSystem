package com.maryj.authenticationsystem.service.impl;

import com.maryj.authenticationsystem.dto.*;
import com.maryj.authenticationsystem.entity.Otp;
import com.maryj.authenticationsystem.repository.OtpRepository;
import com.maryj.authenticationsystem.util.AppUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
 public class OtpService {

    private final OtpRepository otpRepository;
    private final EmailService emailService;

    public Response sendOtp(OtpRequest otpRequest) {

        String otp = AppUtil.generateOtp();
        Otp existingOtp = otpRepository.findByEmail(otpRequest.getEmail());
        if (existingOtp != null) {
            otpRepository.delete(existingOtp);
        }
        log.info("otp: {}",otp);
        otpRepository.save(Otp.builder()
                .email(otpRequest.getEmail())
                .otp(otp)
                .expiresAt(LocalDateTime.now().plusMinutes(2)).build());


        emailService.sendEmail(EmailDetail.builder()
                .subject("DO NOT DISCLOSE!!!")
                .recipient(otpRequest.getEmail())
                .messageBody("This organization has sent you an otp. this otp expires in 2 minutes" + otp).build());

        return Response.builder()
                .statusCode(200)
                .responseMessage("SUCCESS").build();
    }


    public Response validateOtp(OtpValidationRequest otpValidationRequest) {
        Otp otp =otpRepository.findByEmail(otpValidationRequest.getEmail());
        log.info("Email: {}", otpValidationRequest.getEmail());
        if (otp == null) {
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("You have not sent an otp")
                    .build();
        }
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Expired Otp").build();
        }

        if (!otp.getOtp().equals(otpValidationRequest.getOtp())) {
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Invalid otp").build();
        }
    return Response.builder()
            .statusCode(200)
            .responseMessage("SUCCESS")
            .otpResponse(OtpResponse.builder()
                    .isOtpValid(true).build()).build();
    }


}
