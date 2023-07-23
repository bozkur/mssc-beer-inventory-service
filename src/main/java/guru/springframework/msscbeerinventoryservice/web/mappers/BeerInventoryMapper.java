package guru.springframework.msscbeerinventoryservice.web.mappers;

import guru.springframework.msscbeerinventoryservice.domain.BeerInventory;
import guru.springframework.msscbeerinventoryservice.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;

/**
 * @author cevher
 */
@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventoryDto inventoryToDto(BeerInventory domain);

    BeerInventory dtoToInventory(BeerInventoryDto dto);
}
