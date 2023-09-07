package com.customer.api.domain.Loan;

public enum LoanType {
    PERSONAL(4, "interest_rate"),
    GUARANTEED(3, "interest_rate"),
    CONSIGNMENT(2, "interest_rate");

    LoanType(int value, String interestRate) {
    }
}
