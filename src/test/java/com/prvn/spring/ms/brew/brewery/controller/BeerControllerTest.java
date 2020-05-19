package com.prvn.spring.ms.brew.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prvn.spring.ms.brew.brewery.domain.Beer;
import com.prvn.spring.ms.brew.brewery.mappers.DateMapper;
import com.prvn.spring.ms.brew.brewery.model.BeerDto;
import com.prvn.spring.ms.brew.brewery.service.BeerService;
import com.prvn.spring.ms.brew.brewery.utils.ResourceURIs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
*/


/**
 * File    : BeerControllerTest
 * Created : 19/05/20
 * Last Changed  : 19/05/20 10:44 AM Tue
 * Author  : apple
 * History :
 * Initial impound
 */
// for RESTDocs
@AutoConfigureRestDocs
// Extending REST Doc extensions
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.prvn.spring.ms")
class BeerControllerTest {

    @MockBean
    BeerService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DateMapper dateMapper;

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
        beerDtoReturn = BeerDto.builder().id(UUID.randomUUID()).brandCode("KFESC")
                .brandName("KINGFISHER EXTRA STRONG BEER 500CAN")
                .beerName("KigFisher")
                .beerStyle("500CAN")
                .upc("8901020142324")
                .createdDate(dateMapper.asTimestamp(OffsetDateTime.now()))
                .lastUpdated(dateMapper.asTimestamp(OffsetDateTime.now()))
                .build();
        System.out.println("SetUp call");
    }

    // SPRING Rest Docs With Testcases
    @Test
    void testGetBeerById() throws Exception {
        Mockito.when(service.getBeerById(any())).thenReturn(beerDtoReturn);
        mockMvc.perform(get(ResourceURIs.BEER_RESOURCE + "/{beerId}", UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andDo(document("v1/beer", pathParameters(
                        parameterWithName("beerId").description("UUID of the desired beer")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Beer Id"),
                                fieldWithPath("beerName").description("Name of the Beer"),
                                fieldWithPath("beerStyle").description("Type  of the Beer"),
                                fieldWithPath("brandName").description("Brand  of the Beer"),
                                fieldWithPath("brandCode").description("Brand Code  of the Beer"),
                                fieldWithPath("upc").description("Universal Product code of the Beer"),
                                fieldWithPath("createdDate").description("Created date  of the Beer"),
                                fieldWithPath("lastUpdated").description("Modified date  of the Beer")

                        )));
    }

    @Test
    void testSaveBeer() throws Exception {
        String asString = mapper.writeValueAsString(beerDtoJson);
        Mockito.when(service.saveBeer(beerDtoJson)).thenReturn(beerDtoReturn);
        mockMvc.perform(post(ResourceURIs.BEER_RESOURCE).contentType(MediaType.APPLICATION_JSON_UTF8).content(asString))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", ResourceURIs.BEER_RESOURCE + "/" + beerDtoReturn.getId()))
                .andDo(document("v1/beer", requestFields(
                        fieldWithPath("id").ignored(),
                        fieldWithPath("beerName").description("Name of the Beer"),
                        fieldWithPath("beerStyle").description("Type  of the Beer"),
                        fieldWithPath("brandName").description("Brand  of the Beer"),
                        fieldWithPath("brandCode").description("Brand Code  of the Beer"),
                        fieldWithPath("upc").description("Universal Product code of the Beer"),
                        fieldWithPath("createdDate").ignored(),
                        fieldWithPath("lastUpdated").ignored()
                )));
    }

}