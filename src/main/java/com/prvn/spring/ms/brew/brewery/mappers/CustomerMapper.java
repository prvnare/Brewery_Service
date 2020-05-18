package com.prvn.spring.ms.brew.brewery.mappers;

import com.prvn.spring.ms.brew.brewery.domain.Customer;
import com.prvn.spring.ms.brew.brewery.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * File    : CustomerMapper
 * Created : 18/05/20
 * Last Changed  : 18/05/20 11:51 AM Mon
 * Author  : apple
 * History :
 * Initial impound
 */

@Mapper
public interface CustomerMapper {
    Customer customerDTOtoCustomer(CustomerDto customerDto);
    CustomerDto customerToCustomerDto(Customer customer);
}
