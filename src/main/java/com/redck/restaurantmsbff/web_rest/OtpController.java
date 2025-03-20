package com.redck.restaurantmsbff.web_rest;

import com.redck.restaurantmsbff.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController implements ApiController
{
    private OtpService otpService;

    private static final String SEND_OTP = "/send/otp/{userUid}";
    private static final String VERIFY_OTP = "/verify/{userUid}/otp/{otpCode}";

    public OtpController(final OtpService otpService)
    {
        this.otpService = otpService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping(value = SEND_OTP)
    public ResponseEntity<String> sendOtp(@PathVariable String userUid)
    {
        try
        {
            otpService.sendOtp(userUid);
            return ResponseEntity.ok("OTP sent successfully");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body("Failed to send OTP.");
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping(value = VERIFY_OTP)
    public ResponseEntity<String> verifyOtp(@PathVariable String userUid, @PathVariable String otpCode)
    {
        if(otpService.verifyOtp(userUid, otpCode))
        {
            return ResponseEntity.ok("OTP verified successfully!");
        }
        else
        {
            return ResponseEntity.status(400).body("Invalid or expired OTP.");
        }
    }
}
