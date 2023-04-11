package com.example.hive.repository;

import com.example.hive.entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, String> {
    Optional<PaymentLog> findByTransactionReference(String reference);
}