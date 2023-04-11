package com.example.hive.controller;


import com.example.hive.dto.request.TaskerPaymentRequest;
import com.example.hive.dto.response.AppResponse;
import com.example.hive.dto.response.PayStackResponse;
import com.example.hive.dto.response.VerifyTransactionResponse;
import com.example.hive.service.PaymentService;
import com.example.hive.dto.response.WalletResponseDto;
import com.example.hive.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/transaction")
@Slf4j
@RequiredArgsConstructor
public class TransactionController {
    private final PaymentService paymentService;
    private final WalletService walletService;

    @PostMapping("/payment")
    @Operation(summary = "Make Payment to Doer via Paystack Gateway", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)))})
    public ResponseEntity<AppResponse<PayStackResponse>> taskerInitiatesPayment(@RequestBody @Valid final TaskerPaymentRequest taskerPaymentRequest, Principal principal) throws Exception {
       // we need to make a call to the paystack api to make the payment and get the response
        PayStackResponse response =  paymentService.initiatePaymentAndSaveToPaymentLog(taskerPaymentRequest, principal);
        return ResponseEntity.ok(AppResponse.buildSuccessTxn(response));
    }

    @PostMapping("/verifyPayment")
    @Operation(summary = "Verify Payment Transaction", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)))})
    public ResponseEntity<AppResponse<VerifyTransactionResponse>> verifyAndCompletePayment(@RequestParam String reference, Principal principal) throws Exception {
        // we need to check the status of the payment and complete the transaction
        log.info("verifying- for :: [{}]", reference );
        VerifyTransactionResponse response = paymentService.verifyAndCompletePayment(reference, principal);
        return ResponseEntity.ok(AppResponse.buildSuccessTxn(response));
    }


    public ResponseEntity<AppResponse<WalletResponseDto>> viewDoerWallet(Principal principal) throws Exception {

        WalletResponseDto response = walletService.getWalletByUser(principal);
        return ResponseEntity.ok(AppResponse.buildSuccessTxn(response));
    }



}
