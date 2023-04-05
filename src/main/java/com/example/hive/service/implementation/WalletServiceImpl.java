package com.example.hive.service.implementation;

import com.example.hive.constant.TransactionStatus;
import com.example.hive.constant.TransactionType;
<<<<<<< HEAD
import com.example.hive.entity.Task;
=======
import com.example.hive.dto.response.WalletResponseDto;
>>>>>>> d85187f5437ee9b8ce76df3d7912be2ab0c76a73
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
<<<<<<< HEAD
import com.example.hive.utils.event.WalletFundingEvent;
=======
>>>>>>> d85187f5437ee9b8ce76df3d7912be2ab0c76a73
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
<<<<<<< HEAD
=======
import java.security.Principal;
>>>>>>> d85187f5437ee9b8ce76df3d7912be2ab0c76a73
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
<<<<<<< HEAD
    public boolean creditDoerWallet(User doer, BigDecimal creditAmount, Task task){
=======
    public boolean creditDoerWallet(User doer, BigDecimal creditAmount){
>>>>>>> d85187f5437ee9b8ce76df3d7912be2ab0c76a73

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
            transactionLog.setDoer(doer);
            transactionLog.setTransactionType(TransactionType.DEPOSIT);
            transactionLog.setTransactionStatus(TransactionStatus.SUCCESS);

            transactionLogRepository.save(transactionLog);
                eventPublisher.publishEvent(new SuccessfulCreditEvent(doer, transactionLog));
<<<<<<< HEAD
                eventPublisher.publishEvent(new WalletFundingEvent(this, task));
=======
>>>>>>> d85187f5437ee9b8ce76df3d7912be2ab0c76a73

                return true;

        }

    }

<<<<<<< HEAD




=======
    @Override
    public WalletResponseDto getWalletByUser(Principal principal) {

        //get the user from the princial
        User doer = userRepository.findByEmail(principal.getName()).get();
        Wallet getWallet = walletRepository.findByUser(doer).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return new WalletResponseDto(getWallet.getAccountBalance());
    }
>>>>>>> d85187f5437ee9b8ce76df3d7912be2ab0c76a73


}
