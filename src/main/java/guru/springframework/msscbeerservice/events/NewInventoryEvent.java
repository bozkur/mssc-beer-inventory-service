package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;

import java.io.Serial;

/**
 * @author cevher
 */
@AllArgsConstructor
public class NewInventoryEvent extends BeerEvent{
    @Serial
    private static final long serialVersionUID = -1767703357372754708L;

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}