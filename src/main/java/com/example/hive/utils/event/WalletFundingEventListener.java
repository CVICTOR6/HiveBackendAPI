package com.example.hive.utils.event;

import com.example.hive.entity.Task;
import com.example.hive.exceptions.CustomException;
import com.example.hive.service.implementation.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;

@RequiredArgsConstructor
@Log4j2
public class WalletFundingEventListener implements ApplicationListener<WalletFundingEvent> {

    private final NotificationServiceImpl notificationService;

    @Override
    public void onApplicationEvent(WalletFundingEvent event) {
        Task task = event.getTask();

        try {
            notificationService.walletFundingNotification(task);
        } catch (CustomException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
