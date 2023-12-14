package com.example.springmodulithwithddd.borrow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patronId;

    private int loanDurationInDays;

    private LocalDate dateOfReturn;

    private LocalDate dateOfIssue;

    private String bookBarcode;

    private Long version;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public Loan(String bookBarcode) {
        this.bookBarcode = bookBarcode;
        this.dateOfIssue = LocalDate.now();
        this.loanDurationInDays = 14;
        this.status = LoanStatus.ACTIVE;
    }


    public static Loan of(String bookBarcode) {
        return new Loan(bookBarcode);
    }

    public boolean isActive() {
        return LoanStatus.ACTIVE.equals(status);
    }

    public boolean isOverdue() {
        return LoanStatus.OVERDUE.equals(status);
    }

    public boolean isCompleted() {
        return LoanStatus.OVERDUE.equals(status);
    }

    public void complete() {
        if (isCompleted()) {
            throw new IllegalStateException("Loan is not active");
        }
        this.status = LoanStatus.COMPLETED;
        this.dateOfReturn = LocalDate.now();
    }

    public enum LoanStatus {
        ACTIVE, COMPLETED, OVERDUE
    }


}
