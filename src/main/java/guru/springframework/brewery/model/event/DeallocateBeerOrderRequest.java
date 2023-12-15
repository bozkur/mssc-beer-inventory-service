package guru.springframework.brewery.model.event;

import guru.springframework.brewery.model.BeerOrderDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author cevher
 */
@Data
@RequiredArgsConstructor
public class DeallocateBeerOrderRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 7513966075503896945L;
    private final BeerOrderDto beerOrderDto;
}
