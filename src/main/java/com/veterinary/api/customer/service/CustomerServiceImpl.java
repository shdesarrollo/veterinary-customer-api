package com.veterinary.api.customer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinary.api.customer.dto.CustomerDTO;
import com.veterinary.api.customer.entity.CustomerEntity;
import com.veterinary.api.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ObjectMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> mapper.convertValue(customer, CustomerDTO.class))
                .toList();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> mapper.convertValue(customer, CustomerDTO.class))
                .orElse(null);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.save(mapper.convertValue(customerDTO, CustomerEntity.class));
        return mapper.convertValue(customerEntity, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        if (customerEntity != null) {
            customerEntity = customerRepository.save(mapper.convertValue(customerDTO, CustomerEntity.class));
            return mapper.convertValue(customerEntity, CustomerDTO.class);
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
