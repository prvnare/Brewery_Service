package com.prvn.spring.ms.brew.brewery.service;

import com.prvn.spring.ms.brew.brewery.model.BeerDto;

import java.util.UUID;

/**
 * File    : BeerService
 * Created : 15/05/20
 * Last Changed  : 15/05/20 1:14 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */
public interface BeerService {
    // ## methods ##
    BeerDto getBeerById(UUID beerId);
    BeerDto saveBeer(BeerDto beerDto);
    void update(String beerId, BeerDto beerDto);
    void deleteBeer(UUID beerId);
}
