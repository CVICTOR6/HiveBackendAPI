package com.example.hive.repository;

import com.example.hive.entity.EscrowWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscrowWalletRepository extends JpaRepository<EscrowWallet, String > {
}
