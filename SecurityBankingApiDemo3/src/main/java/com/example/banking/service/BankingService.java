package com.example.banking.service;

import com.example.banking.model.TransferRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BankingService {

    @PreAuthorize("hasRole('USER')")
    public String processTransfer(TransferRequest request) {
        // Simulate transfer logic (e.g., database interaction)
        return "Transfer of " + request.getAmount() + " from "
                + request.getSourceAccount() + " to "
                + request.getDestinationAccount() + " successful";
    }
}
