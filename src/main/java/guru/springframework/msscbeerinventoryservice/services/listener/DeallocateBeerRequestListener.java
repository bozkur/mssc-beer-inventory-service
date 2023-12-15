package guru.springframework.msscbeerinventoryservice.services.listener;

import guru.springframework.brewery.model.event.DeallocateBeerOrderRequest;
import guru.springframework.msscbeerinventoryservice.config.JmsConfig;
import guru.springframework.msscbeerinventoryservice.services.AllocationService;
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
public class DeallocateBeerRequestListener {

    private final AllocationService allocationService;

    @Transactional
    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateBeerOrderRequest request) {
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
