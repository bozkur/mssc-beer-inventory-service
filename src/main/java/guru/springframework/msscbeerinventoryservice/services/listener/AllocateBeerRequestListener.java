package guru.springframework.msscbeerinventoryservice.services.listener;

import guru.springframework.brewery.model.event.AllocateBeerOrderRequest;
import guru.springframework.brewery.model.event.AllocateBeerOrderResult;
import guru.springframework.msscbeerinventoryservice.config.JmsConfig;
import guru.springframework.msscbeerinventoryservice.services.AllocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cevher
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AllocateBeerRequestListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateBeerOrderRequest request) {
        var builder = AllocateBeerOrderResult.builder();
        builder.beerOrder(request.getBeerOrderDto());

        try {
            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());
            builder.pendingInventory(!allocationResult);
            builder.allocationError(false);
        } catch (Exception ex) {
            log.error("Allocation failed for order Id: {}", request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
