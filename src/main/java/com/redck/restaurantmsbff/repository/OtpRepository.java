package com.redck.restaurantmsbff.repository;

import com.redck.restaurantmsbff.domain.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>
{
    Optional<Otp> findByEmailAndOtpCode(String email, String otpCode);

    // Delete expired OTPs
    @Modifying
    @Transactional
    @Query("DELETE FROM Otp o WHERE o.expiresAt < :time")
    int deleteExpiredOtps(@Param("time") LocalDateTime time);

    // Delete expired OTPs for a specific user before inserting a new one
    @Modifying
    @Transactional
    @Query("DELETE FROM Otp o WHERE o.email = :email AND o.expiresAt < :time")
    void deleteByEmailAndExpiresAtBefore(@Param("email") String email, @Param("time") LocalDateTime time);
}
