package com.maryj.authenticationsystem.repository;

import com.maryj.authenticationsystem.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Long> {

    Otp findByEmail(String email);
}
