package guru.springframework.msscbeerinventoryservice.services;

import guru.springframework.msscbeerinventoryservice.config.JmsConfig;
import guru.springframework.msscbeerinventoryservice.domain.BeerInventory;
import guru.springframework.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import guru.springframework.brewery.model.event.NewInventoryEvent;
import guru.springframework.brewery.model.BeerDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author cevher
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NewInventoryEventListener {

    private final BeerInventoryRepository inventoryRepository;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent inventoryEvent) {
        BeerDto beerDto = inventoryEvent.getBeerDto();
        Integer quantityInHand = beerDto.getQuantityInHand();
        BeerInventory beerInventory = BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityInHand(quantityInHand)
                .build();
        log.debug("Adding inventory for beer {} with quantity {}", beerDto.getBeerName(), quantityInHand);
        inventoryRepository.save(beerInventory);
    }
}
