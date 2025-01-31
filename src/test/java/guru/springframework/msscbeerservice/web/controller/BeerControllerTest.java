package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import guru.springframework.msscbeerservice.services.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Creo la classe a partire da BeerController
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    // Object Jackson Mapper
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    //@Test
    void getBeerByIdOldErr() throws Exception {

        boolean lezione71 = true ;

        if (lezione71)
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        else { // adesso va in errore prima funzionava
            given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());


            mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

    }

    //@Test
    void saveNewBeerOldErr() throws Exception {

        // LEZIONE 84
        // Non puo' essere un oggetto a causa
        // dei vincoli che abbiamo messo nella classe BeerDto
        // Viene pertanto creato un oggetto e ritornato
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        /*mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
        */
        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    //@Test
    void updateBeerByIdOldErr() throws Exception {
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

        // LEZIONE 84
        // Non puo' essere un oggetto a causa
        // dei vincoli che abbiamo messo nella classe BeerDto
        // Viene pertanto creato un oggetto e ritornato
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDtoOldErr(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    @Test
    void getBeerById() throws Exception {

        given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}