package com.prvn.spring.ms.brew.brewery.controller;

import com.prvn.spring.ms.brew.brewery.model.BeerDto;
import com.prvn.spring.ms.brew.brewery.service.BeerService;
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
 * File    : BeerController
 * Created : 15/05/20
 * Last Changed  : 15/05/20 12:53 AM Fri
 * Author  : apple
 * History :
 * Initial impound
 */

@Validated //to perform the  method level argument validation
@RestController
@Slf4j
@RequestMapping(ResourceURIs.BEER_RESOURCE)
public class BeerController {
    // ## services ##
    private final BeerService service;

    public BeerController(BeerService service) {
        this.service = service;
    }

    // ## Request Handlers ##

    @GetMapping(value = "/{beerId}")
    public ResponseEntity<BeerDto> getBeerDetails(@NotNull @PathVariable("beerId") UUID beerId) {
        log.debug("Generated UUID : {}", beerId);
        BeerDto beerById = service.getBeerById(beerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(beerById, headers, HttpStatus.OK);
    }

    //@Valid will do the been validation
    // BeerDto will be having the validation rules.
    @PostMapping
    public ResponseEntity<BeerDto> creatBeer(@Valid @RequestBody BeerDto beerDto) {
        BeerDto savedBeer = service.saveBeer(beerDto);
        log.debug("Saved Beer Details : {} ", savedBeer);
        HttpHeaders headers = new HttpHeaders();
        //TODO :  Add host name here.
        headers.add("Location", ResourceURIs.BEER_RESOURCE + "/" + savedBeer.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity handelUpdate(@PathVariable String beerId, @Valid @RequestBody BeerDto beerDto) {
        service.update(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        service.deleteBeer(beerId);
    }

    // ## Handling the Exceptions for validations ##


}
