package guru.springframework.msscbeerinventoryservice.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author cevher
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerInventory extends BaseEntity {

    @Builder
    public BeerInventory(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, UUID beerId, String upc, Integer quantityInHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.upc = upc;
        this.quantityInHand = quantityInHand;
    }

    private UUID beerId;
    private String upc;
    private Integer quantityInHand = 0;
}
