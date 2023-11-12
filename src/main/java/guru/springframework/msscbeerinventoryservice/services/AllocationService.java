package guru.springframework.msscbeerinventoryservice.services;

import guru.springframework.brewery.model.BeerOrderDto;

/**
 * @author cevher
 */
public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerDto);
}
