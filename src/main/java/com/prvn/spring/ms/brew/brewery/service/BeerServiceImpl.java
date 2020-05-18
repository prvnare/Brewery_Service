package com.prvn.spring.ms.brew.brewery.service;

import com.prvn.spring.ms.brew.brewery.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * File    : BeerServiceImpl
 * Created : 15/05/20
 * Last Changed  : 15/05/20 1:14 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    // ## public methods ##
    @Override
    public BeerDto getBeerById(UUID beerId) {
        log.debug("BeerId : {}", beerId);
        return BeerDto.builder()
                .id(beerId)
                .brandCode("KFESC")
                .brandName("KINGFISHER EXTRA STRONG BEER 500CAN")
                .beerName("KigFisher")
                .beerStyle("500CAN")
                .upc("8901020142324")
                .build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void update(String beerId, BeerDto beerDto) {
        //todo imp -- Implement the real logic
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("deleed");
    }
}
