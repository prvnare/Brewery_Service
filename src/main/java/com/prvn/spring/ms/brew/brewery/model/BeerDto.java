package com.prvn.spring.ms.brew.brewery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * File   : BeerDto
 * Created : 15/05/20
 * Last Changed  : 15/05/20 12:39 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto {
    @Null
    private UUID id;
    @NotBlank
    private String beerName;
    @NotBlank
    private String beerStyle;
    @NotBlank
    private String brandName;
    @NotBlank
    private String brandCode;
    @NotBlank
    private String upc;

    private Timestamp createdDate;
    private Timestamp lastUpdated;
}
