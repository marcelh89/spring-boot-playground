package com.example.springmodulithwithddd.borrow;

import com.example.springmodulithwithddd.inventory.BookManagement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class LoanManagementIT {

    @Autowired
    LoanManagement loans;

    @Autowired
    BookManagement books;

    @Test
    void shouldCreateLoanAndIssueBookOnCheckout() {
        var loanDto = loans.checkout("13268510");
        assertThat(loanDto.status()).isEqualTo(Loan.LoanStatus.ACTIVE);
        assertThat(loanDto.bookBarcode()).isEqualTo("13268510");
        assertThat(books.locate(1L).get().status()).hasToString("ISSUED");
    }

    @Test
    void shouldCompleteLoanAndReleaseBookOnCheckin() {
        var loan = loans.checkin(10L);
        assertThat(loan.status()).isEqualTo(Loan.LoanStatus.COMPLETED);
        assertThat(books.locate(2L).get().status()).hasToString("AVAILABLE");
    }

    @Test
    void shouldListActiveLoans() {
        var loans = this.loans.activeLoans();
        assertThat(loans).hasSize(1);
    }


}
