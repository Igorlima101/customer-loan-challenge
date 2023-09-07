package com.customer.api.domain.customer;


import com.customer.api.domain.Loan.LoanType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;

@Entity(name = "customers")
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;

    private String cpf;

    private String name;

    private Integer income;

    private String location;

    @JsonProperty("availableLoans")
    private ArrayList loan = new ArrayList<>();

    public void addLoan(LoanType loanType){
        loan.add(loanType);
    }
}
