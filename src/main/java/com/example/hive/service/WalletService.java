package com.example.hive.service;

import com.example.hive.entity.Task;
import com.example.hive.entity.User;

import java.math.BigDecimal;
import com.example.hive.dto.response.WalletResponseDto;
import com.example.hive.entity.Wallet;

import java.security.Principal;

public interface WalletService {

    boolean creditDoerWallet(User doer, BigDecimal creditAmount, Task task);

    WalletResponseDto getWalletByUser(Principal principal);

    boolean fundTaskerWallet(User tasker, BigDecimal amountToFund);

    boolean debitTaskerWalletToEscrow(Wallet wallet, BigDecimal amount);
}
