package com.example.hive.service;

import com.example.hive.entity.Task;
import com.example.hive.entity.TransactionLog;
import com.example.hive.entity.User;

import java.math.BigDecimal;
import com.example.hive.dto.response.WalletResponseDto;
import com.example.hive.entity.TransactionLog;
import com.example.hive.entity.User;
import com.example.hive.entity.Wallet;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.UUID;

public interface WalletService {

    boolean creditDoerWallet(User doer, BigDecimal creditAmount, Task task);

    WalletResponseDto getWalletByUser(Principal principal);

}
