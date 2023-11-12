package guru.springframework.msscbeerinventoryservice.bootstrap;

import guru.springframework.msscbeerinventoryservice.domain.BeerInventory;
import guru.springframework.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author cevher
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerInventoryBootstrap implements CommandLineRunner {
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerInventoryRepository beerInventoryRepository;

    @Override
    public void run(String... args) {
        if (beerInventoryRepository.count() == 0) {
            loadInitialInventory();
        }
    }

    private void loadInitialInventory() {
        createBeerInventoryItem(BEER_1_UUID, BEER_1_UPC);
        createBeerInventoryItem(BEER_2_UUID, BEER_2_UPC);
        createBeerInventoryItem(BEER_3_UUID, BEER_3_UPC);
    }

    private void createBeerInventoryItem(UUID uuid, String upc) {
        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(uuid).upc(upc).quantityOnHand(50).build());
    }
}
