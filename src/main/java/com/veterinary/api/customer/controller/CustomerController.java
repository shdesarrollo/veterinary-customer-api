package com.veterinary.api.customer.controller;

import com.veterinary.api.customer.dto.CustomerDTO;
import com.veterinary.api.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    private List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    private CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/")
    private CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    private CustomerDTO updateCustomer(@PathVariable Long id, CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    private void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

}
