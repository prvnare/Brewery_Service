package com.prvn.spring.ms.brew.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prvn.spring.ms.brew.brewery.domain.Beer;
import com.prvn.spring.ms.brew.brewery.model.BeerDto;
import com.prvn.spring.ms.brew.brewery.service.BeerService;
import com.prvn.spring.ms.brew.brewery.utils.ResourceURIs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * File    : BeerControllerTest
 * Created : 19/05/20
 * Last Changed  : 19/05/20 10:44 AM Tue
 * Author  : apple
 * History :
 * Initial impound
 */

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    Beer beer;
    BeerDto beerDtoJson;
    BeerDto beerDtoReturn;

    @BeforeEach
    void setUp() {
        beer = Beer.builder()
                .id(UUID.randomUUID())
                .brandCode("KFESC")
                .brandName("KINGFISHER EXTRA STRONG BEER 500CAN")
                .beerName("KigFisher")
                .beerStyle("500CAN")
                .upc("8901020142324")
                .build();
        beerDtoJson = BeerDto.builder()
                .id(null)
                .brandCode("KFESC")
                .brandName("KINGFISHER EXTRA STRONG BEER 500CAN")
                .beerName("KigFisher")
                .beerStyle("500CAN")
                .upc("8901020142324")
                .build();
        beerDtoReturn = BeerDto.builder().id(UUID.randomUUID()).build();
        System.out.println("SetUp call");
    }

    @Test
    void testGetBeerById() throws Exception {
        mockMvc.perform(get(ResourceURIs.BEER_RESOURCE + "/" + UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    void testSaveBeer() throws Exception {
        String asString = mapper.writeValueAsString(beerDtoJson);
        Mockito.when(service.saveBeer(beerDtoJson)).thenReturn(beerDtoReturn);
        mockMvc.perform(post(ResourceURIs.BEER_RESOURCE).contentType(MediaType.APPLICATION_JSON_UTF8).content(asString))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", ResourceURIs.BEER_RESOURCE + "/" + beerDtoReturn.getId()));
    }

}