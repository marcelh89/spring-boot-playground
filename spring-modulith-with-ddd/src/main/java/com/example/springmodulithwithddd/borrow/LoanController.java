package com.example.springmodulithwithddd.borrow;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
class LoanController {

    private final LoanManagement loans;

    @PostMapping("/loans")
    ResponseEntity<LoanDto> checkoutBook(@RequestBody CheckoutRequest request) {
        var barcode = request.barcode();
        var loanDto = loans.checkout(barcode);
        return ResponseEntity.ok(loanDto);
    }

    @DeleteMapping("/loans/{id}")
    ResponseEntity<LoanDto> checkinBook(@PathVariable("id") Long loanId) {
        var loanDto = loans.checkin(loanId);
        return ResponseEntity.ok(loanDto);
    }

    @GetMapping("/loans/{id}")
    ResponseEntity<LoanDto> viewSingleLoan(@PathVariable("id") Long loanId) {
        return loans.locate(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/loans")
    ResponseEntity<List<LoanWithBookDto>> viewActiveLoans() {
        return ResponseEntity.ok(loans.activeLoans());
    }

    record CheckoutRequest(String barcode) {
    }
}
