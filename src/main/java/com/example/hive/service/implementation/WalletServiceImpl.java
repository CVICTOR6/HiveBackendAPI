package com.example.hive.service.implementation;

import com.example.hive.constant.TransactionStatus;
import com.example.hive.constant.TransactionType;
import com.example.hive.entity.Task;
import com.example.hive.dto.response.WalletResponseDto;
import com.example.hive.entity.TransactionLog;
import com.example.hive.entity.User;
import com.example.hive.entity.Wallet;
import com.example.hive.enums.Role;
import com.example.hive.exceptions.CustomException;
import com.example.hive.repository.TransactionLogRepository;
import com.example.hive.repository.UserRepository;
import com.example.hive.repository.WalletRepository;
import com.example.hive.service.WalletService;
import com.example.hive.utils.event.SuccessfulCreditEvent;
import com.example.hive.utils.event.WalletFundingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final TransactionLogRepository transactionLogRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean creditDoerWallet(User doer, BigDecimal creditAmount, Task task){

        log.info("Crediting doer wallet{}", doer.getFullName()) ;
        //check role of user
        if (!doer.getRole().equals(Role.DOER)) {
            throw new CustomException("User is not a doer");
        }
        else {
            //check if user has a wallet
         Wallet wallet =  walletRepository.findByUser(doer).orElseThrow(() -> new CustomException("User does not have a wallet"));
            log.info("I found wallet balance of {}", wallet.getAccountBalance()) ;

            if (wallet.getAccountBalance() == null) {wallet.setAccountBalance(creditAmount);}

             //credit wallet
            else { wallet.setAccountBalance(wallet.getAccountBalance().add(creditAmount));}
            log.info("NOW I found wallet balance of {}", wallet.getAccountBalance()) ;

            TransactionLog transactionLog = new TransactionLog ();
            transactionLog.setAmount(creditAmount);
            transactionLog.setUser(doer);
            transactionLog.setTransactionType(TransactionType.DEPOSIT);
            transactionLog.setTransactionStatus(TransactionStatus.SUCCESS);

            transactionLogRepository.save(transactionLog);
                eventPublisher.publishEvent(new SuccessfulCreditEvent(doer, transactionLog));
                eventPublisher.publishEvent(new WalletFundingEvent(this, task));

                return true;

        }

    }


    @Override
    public WalletResponseDto getWalletByUser(Principal principal) {

        //get the user from the princial
        User user = userRepository.findByEmail(principal.getName()).get();
        Wallet getWallet = walletRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return new WalletResponseDto(getWallet.getAccountBalance());
    }

    @Override
    public boolean fundTaskerWallet(User tasker, BigDecimal amountToFund) {
        log.info("Crediting tasker wallet{}", tasker.getFullName()) ;
        //check role of user
        if (!tasker.getRole().equals(Role.TASKER)) {
            throw new CustomException("User is not a tasker");
        }
        else {
            //check if user has a wallet
            Wallet wallet =  walletRepository.findByUser(tasker).orElseThrow(() -> new CustomException("User does not have a wallet"));
            log.info("I found wallet balance of {}", wallet.getAccountBalance()) ;

            if (wallet.getAccountBalance() == null) {wallet.setAccountBalance(amountToFund);}

            //credit wallet
            else { wallet.setAccountBalance(wallet.getAccountBalance().add(amountToFund));}
            log.info("NOW I found wallet balance of {}", wallet.getAccountBalance()) ;

            TransactionLog transactionLog = new TransactionLog ();
            transactionLog.setAmount(amountToFund);
            transactionLog.setUser(tasker);
            transactionLog.setTransactionType(TransactionType.DEPOSIT);
            transactionLog.setTransactionStatus(TransactionStatus.SUCCESS);

            transactionLogRepository.save(transactionLog);
            eventPublisher.publishEvent(new SuccessfulCreditEvent(tasker, transactionLog));

            return true;

        }
    }

    @Override
    public boolean debitTaskerWalletToEscrow(Wallet wallet, BigDecimal amount) {
        log.info("Debiting tasker wallet{}", wallet.getUser().getFullName()) ;
        //check role of user
        if (!wallet.getUser().getRole().equals(Role.TASKER)) {
            throw new CustomException("User is not a tasker role");
        }
        else {
            //check if user has a wallet
            Wallet getWallet =  walletRepository.findByUser(wallet.getUser()).orElseThrow(() -> new CustomException("User does not have a wallet"));
            log.info("I found wallet balance of {}", getWallet.getAccountBalance()) ;

            if (getWallet.getAccountBalance() == null) {throw new CustomException("Insufficient funds");}

            //debit wallet for task creation
            else { getWallet.setAccountBalance(getWallet.getAccountBalance().subtract(amount));}
            log.info("NOW I found wallet balance of {}", getWallet.getAccountBalance()) ;

            walletRepository.save(wallet);
            return true;

        }
    }


}
