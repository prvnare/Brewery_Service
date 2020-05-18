package com.prvn.spring.ms.brew.brewery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

/**
 * File    : CustomerDto
 * Created : 15/05/20
 * Last Changed  : 15/05/20 1:58 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    // ## Fields ##
    @Null
    private UUID id;
    @NotNull
    @NotBlank
    @Min(3)
    @Max(100)
    private String firstName;
    @NotNull
    @NotBlank
    @Min(3)
    @Max(100)
    private String lastName;
    @Email
    private String emailId;
    private String mobileNumber;

}
