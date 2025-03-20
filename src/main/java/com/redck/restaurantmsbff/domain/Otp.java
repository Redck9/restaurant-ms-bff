package com.redck.restaurantmsbff.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Otp
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String email;
   private String otpCode;
   private LocalDateTime expiresAt;

   public Otp(){}

    public Otp(String email, String otpCode, LocalDateTime expiresAt)
    {
        this.email = email;
        this.otpCode = otpCode;
        this.expiresAt = expiresAt;
    }

    public String getEmail()
    {
        return email;
    }

    public String getOtpCode()
    {
        return otpCode;
    }

    public LocalDateTime getExpiresAt()
    {
        return expiresAt;
    }
}
