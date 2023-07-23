package guru.springframework.msscbeerinventoryservice.web.controllers;

import guru.springframework.msscbeerinventoryservice.domain.BeerInventory;
import guru.springframework.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import guru.springframework.msscbeerinventoryservice.web.mappers.BeerInventoryMapperImpl;
import guru.springframework.msscbeerinventoryservice.web.mappers.DateMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @author cevher
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeerInventoryMapperImpl.class, DateMapper.class})
@WebMvcTest(BeerInventoryController.class)
@Import(BeerInventoryController.class)
class BeerInventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerInventoryRepository beerInventoryRepository;

    @Test
    void listBeersById() throws Exception {
        UUID beerId = UUID.randomUUID();
        when(beerInventoryRepository.findAllByBeerId(beerId)).thenReturn(createInventoryItems(beerId));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/{beerId}/inventory", beerId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].beerId", Matchers.equalTo(beerId.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].beerId", Matchers.equalTo(beerId.toString())));
    }

    private List<BeerInventory> createInventoryItems(UUID beerId) {
        BeerInventory item1 = createInventoryItem(beerId);
        BeerInventory item2 = createInventoryItem(beerId);
        return Arrays.asList(item1, item2);
    }

    private BeerInventory createInventoryItem(UUID beerId) {
        return BeerInventory.builder()
                .id(UUID.randomUUID())
                .beerId(beerId)
                .quantityInHand(10)
                .build();
    }
}