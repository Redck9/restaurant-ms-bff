package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.domain.Otp;
import com.redck.restaurantmsbff.repository.OtpRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService
{

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final OtpRepository otpRepository;
    private final JavaMailSender mailSender;
    private final ClientService clientService;

    @Autowired
    public OtpService(final OtpRepository otpRepository, final JavaMailSender mailSender, final ClientService clientService)
    {
        this.otpRepository = otpRepository;
        this.mailSender = mailSender;
        this.clientService = clientService;
    }

    private String generateOtp()
    {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void sendOtp(String userUid) throws Exception
    {
        String otpCode = generateOtp();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);

        String email = clientService.getUser(userUid).getEmail();
        System.out.println("🔹 Found user: " + email);

        // Remove old OTPs before generating a new one
        otpRepository.deleteByEmailAndExpiresAtBefore(email, LocalDateTime.now());

        //Save OTP in DB
        Otp otp = new Otp(email, otpCode, expiresAt);
        otpRepository.save(otp);

        //Send email
        try
        {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            System.out.println(fromEmail);
            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP is: " + otpCode);
            System.out.println("🔹 Message: " + message);
            // Send the email
            mailSender.send(message);
            System.out.println("🔹 OTP sent to: " + email); // Log success message
        }
        catch (Exception e)
        {
            System.err.println("❌ Failed to send OTP to: " + email);
            e.printStackTrace();
            throw new Exception("Failed to send OTP email", e); // Optionally rethrow or handle exception
        }
    }

    public boolean verifyOtp(String userUid, String otpCode)
    {
        String email = clientService.getUser(userUid).getEmail();
        return otpRepository.findByEmailAndOtpCode(email, otpCode).filter(otp -> otp.getExpiresAt().isAfter(LocalDateTime.now())).isPresent();
    }

    @Scheduled(fixedRate = 3600000) // Runs every 60 minutes
    @Transactional
    public void cleanUpExpiredOtps() {
        int deletedCount = otpRepository.deleteExpiredOtps(LocalDateTime.now());
        System.out.println("🗑 Deleted " + deletedCount + " expired OTPs.");
    }
}
