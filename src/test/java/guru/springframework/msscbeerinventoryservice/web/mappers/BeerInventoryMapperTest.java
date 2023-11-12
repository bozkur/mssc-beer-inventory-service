package guru.springframework.msscbeerinventoryservice.web.mappers;

import guru.springframework.msscbeerinventoryservice.domain.BeerInventory;
import guru.springframework.msscbeerinventoryservice.web.model.BeerInventoryDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author cevher
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeerInventoryMapperImpl.class, DateMapper.class})
class BeerInventoryMapperTest {

    @Autowired
    private BeerInventoryMapper mapper;

     @Autowired
    private DateMapper dateMapper;

    @Test
    @DisplayName("Convert beer inventory domain object to Dto")
    void inventoryToDto() {
        BeerInventory domain = BeerInventory.builder()
                .beerId(UUID.randomUUID())
                .id(UUID.randomUUID())
                .createdDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .quantityOnHand(50)
                .build();

        BeerInventoryDto dto = mapper.inventoryToDto(domain);

        assertThat(dto.getBeerId(), Matchers.equalTo(domain.getBeerId()));
        assertThat(dto.getId(), Matchers.equalTo(domain.getId()));
        assertThat(dto.getQuantityOnHand(), Matchers.equalTo(domain.getQuantityOnHand()));
        assertThat(dto.getCreatedDate(), Matchers.equalTo(dateMapper.timeStamp2OffsetDateTime(domain.getCreatedDate())));
        assertThat(dto.getLastModifiedDate(), Matchers.equalTo(dateMapper.timeStamp2OffsetDateTime(domain.getLastModifiedDate())));


    }

    @Test
    @DisplayName("Convert beer inventory dto to domain object")
    void dtoToInventory() {
        BeerInventoryDto dto = BeerInventoryDto.builder()
                .beerId(UUID.randomUUID())
                .id(UUID.randomUUID())
                .quantityOnHand(50)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .build();

        BeerInventory domain = mapper.dtoToInventory(dto);

        assertThat(domain.getBeerId(), Matchers.equalTo(dto.getBeerId()));
        assertThat(domain.getId(), Matchers.equalTo(dto.getId()));
        assertThat(domain.getQuantityOnHand(), Matchers.equalTo(dto.getQuantityOnHand()));
        assertThat(domain.getCreatedDate(), Matchers.equalTo(dateMapper.offsetDateTime2TimeStamp(dto.getCreatedDate())));
        assertThat(domain.getLastModifiedDate(), Matchers.equalTo(dateMapper.offsetDateTime2TimeStamp(dto.getLastModifiedDate())));

    }
}