package com.example.banking.controller;

import com.example.banking.model.TransferRequest;
import com.example.banking.service.BankingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BankingController {

    private final BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        return bankingService.processTransfer(request);
    }

    @GetMapping("/transfer")
    public String getTransferInfo() {
        return "This endpoint accepts POST requests with transfer details.";
    }
}
