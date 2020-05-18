package com.prvn.spring.ms.brew.brewery.mappers;

import com.prvn.spring.ms.brew.brewery.domain.Beer;
import com.prvn.spring.ms.brew.brewery.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * File    : BeerMapper
 * Created : 18/05/20
 * Last Changed  : 18/05/20 11:46 AM Mon
 * Author  : apple
 * History :
 * Initial impound
 */

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDto(Beer beer);
}
