package com.example.springmodulithwithddd.borrow;


import com.example.springmodulithwithddd.inventory.BookManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanManagement {

    private final LoanRepository loanRepository;
    private final BookManagement books;
    private final LoanMapper mapper;


    public LoanDto checkout(String barcode) {
        books.issue(barcode);
        var loan = Loan.of(barcode);
        var savedLoan = loanRepository.save(loan);
        return mapper.toDto(savedLoan);
    }

    public LoanDto checkin(Long loanId) {
        var loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("no loan found"));
        books.release(loan.getBookBarcode());
        loan.complete();
        return mapper.toDto(loanRepository.save(loan));
    }

    @Transactional(readOnly = true)
    public List<LoanWithBookDto> activeLoans() {
        return loanRepository.findLoansWithStatus(Loan.LoanStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public Optional<LoanDto> locate(Long loanId) {
        return loanRepository.findById(loanId).map(mapper::toDto);
    }


}
