package com.prvn.spring.ms.brew.brewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * File    : Beer
 * Created : 18/05/20
 * Last Changed  : 18/05/20 11:42 AM Mon
 * Author  : apple
 * History :
 * Initial impound
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beer {
    private UUID id;
    private String beerName;
    private String beerStyle;
    private String brandName;
    private String brandCode;
    private String upc;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastUpdated;

}
