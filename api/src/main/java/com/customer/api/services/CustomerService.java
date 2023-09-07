package com.customer.api.services;

import com.customer.api.domain.Loan.LoanType;
import com.customer.api.domain.customer.Customer;
import com.customer.api.exceptions.CustomerNotFoundException;
import com.customer.api.exceptions.StateNotFoundException;
import com.customer.api.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Customer findOneCustomerById(Long id){
        return this.customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
    }

    public Customer createCustomer(Customer customer){

        String[] states = {
                "AC", "AL", "AP", "AM", "BA", "CE", "DF",
                "ES", "GO", "MA", "MT", "MS", "MG", "PA",
                "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RN", "SC", "SP", "SE", "TO"
        };


        boolean containsState = Arrays.stream(states).anyMatch(customer.getLocation()::equals);

        if(!containsState){
            throw new StateNotFoundException("State not found, use two chars to define yours state, example: SP ");
        }


        Customer customer1 = this.validateTypeLoan(customer);
        return this.customerRepository.save(customer1);
    }

    public Customer validateTypeLoan(Customer customer){



        boolean validateCondition = customer.getIncome() > 3000 && customer.getIncome() < 5000 &&
                customer.getAge() < 30 && customer.getLocation().equals("SP");

        if(customer.getIncome() <= 3000){
            customer.addLoan(LoanType.PERSONAL);
        }

        if(validateCondition){
            customer.addLoan(LoanType.PERSONAL);
        }

        if(customer.getIncome() >= 5000){
            customer.addLoan(LoanType.CONSIGNMENT);
        }

        if (customer.getIncome() <= 3000){
            customer.addLoan(LoanType.GUARANTEED);
        }

        if(validateCondition){
            customer.addLoan(LoanType.GUARANTEED);
        }

        return customer;
    }
}
