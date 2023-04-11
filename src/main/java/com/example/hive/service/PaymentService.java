package com.example.hive.service;

import com.example.hive.dto.request.TaskerPaymentRequest;
import com.example.hive.dto.response.PayStackResponse;

import com.example.hive.dto.response.VerifyTransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.security.Principal;

public interface PaymentService {



    PayStackResponse initiatePaymentAndSaveToPaymentLog(TaskerPaymentRequest taskerPaymentRequest, Principal principal) throws Exception;

    VerifyTransactionResponse verifyAndCompletePayment(String reference , Principal principal) throws Exception;
}
