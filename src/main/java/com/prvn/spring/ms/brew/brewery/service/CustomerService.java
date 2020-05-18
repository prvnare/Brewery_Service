package com.prvn.spring.ms.brew.brewery.service;

import com.prvn.spring.ms.brew.brewery.model.CustomerDto;

import java.util.UUID;

/**
 * File    : CustomerService
 * Created : 15/05/20
 * Last Changed  : 15/05/20 2:07 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
    CustomerDto save(CustomerDto customerDto);
    void update(String customerId, CustomerDto customerDto);
    void delete(String customerId);
}
