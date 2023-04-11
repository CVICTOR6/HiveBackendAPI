package com.example.hive.repository;

import com.example.hive.entity.User;
import com.example.hive.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByUser(User user);

}