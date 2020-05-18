package com.prvn.spring.ms.brew.brewery.service;

import com.prvn.spring.ms.brew.brewery.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * File    : CustomerServiceImpl
 * Created : 15/05/20
 * Last Changed  : 15/05/20 2:08 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    // ## Methods ##
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .emailId("test@gmail.com")
                .firstName("Test")
                .lastName("Test")
                .mobileNumber("9959734341")
                .build();
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void update(String customerId, CustomerDto customerDto) {
        log.debug("Update Call invoked : ");
        // Todo impl :  Change the logic as working set
    }

    @Override
    public void delete(String customerId) {
        log.debug("Delete Call invoked : ");
        // Todo impl :  Change the logic as working set
    }
}
