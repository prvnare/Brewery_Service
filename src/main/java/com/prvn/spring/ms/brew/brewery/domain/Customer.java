package com.prvn.spring.ms.brew.brewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * File    : Customer
 * Created : 18/05/20
 * Last Changed  : 18/05/20 11:49 AM Mon
 * Author  : apple
 * History :
 * Initial impound
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private UUID id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;

}
