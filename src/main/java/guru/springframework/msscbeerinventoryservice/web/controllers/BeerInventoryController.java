package guru.springframework.msscbeerinventoryservice.web.controllers;

import guru.springframework.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import guru.springframework.msscbeerinventoryservice.web.mappers.BeerInventoryMapper;
import guru.springframework.msscbeerinventoryservice.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author cevher
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class BeerInventoryController {
    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper inventoryMapper;

    @GetMapping("api/v1/beer/{beerId}/inventory")
    public List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId) {
        log.debug("Finding inventory for beer with Id: {}", beerId);
        return beerInventoryRepository.findAllByBeerId(beerId)
                .stream().map(inventoryMapper::inventoryToDto)
                .collect(Collectors.toList());
    }
}
