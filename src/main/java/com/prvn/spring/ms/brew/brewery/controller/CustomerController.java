package com.prvn.spring.ms.brew.brewery.controller;

import com.prvn.spring.ms.brew.brewery.model.CustomerDto;
import com.prvn.spring.ms.brew.brewery.service.CustomerService;
import com.prvn.spring.ms.brew.brewery.utils.ResourceURIs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * File    : CustomerController
 * Created : 15/05/20
 * Last Changed  : 15/05/20 2:01 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */
@Validated
@RestController
@Slf4j
@RequestMapping(ResourceURIs.CUSTOMER_RESOURCE)
public class CustomerController {

    //## Services ##
    private final CustomerService customerService;

    //## Constructors ##
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //## Request Handlers ##

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@NotNull @PathVariable("customerId") UUID customerId) {
        log.debug("Customer Id : {}", customerId);
        CustomerDto customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomer = customerService.save(customerDto);
        log.debug("Saved Customer Details :  {} ", savedCustomer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", ResourceURIs.CUSTOMER_RESOURCE + "/" + savedCustomer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity handleUpdate(@NotNull @PathVariable String customerId, @Valid @RequestBody CustomerDto customerDto) {
        customerService.update(customerId, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    public void handleDelete(@PathVariable String customerId) {
        customerService.delete(customerId);
    }
}
