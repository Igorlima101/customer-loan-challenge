package com.customer.api.Controller;

import com.customer.api.domain.customer.Customer;
import com.customer.api.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = this.customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Customer> findOneCustomerById(@PathVariable Long id){
        Customer customer = this.customerService.findOneCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer customer1 = this.customerService.createCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

}
