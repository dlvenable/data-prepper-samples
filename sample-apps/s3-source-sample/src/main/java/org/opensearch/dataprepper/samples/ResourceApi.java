package org.opensearch.dataprepper.samples;

import org.opensearch.dataprepper.samples.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class ResourceApi {

    private final Random random;
    private final OrderGenerator orderGenerator;

    @Autowired
    public ResourceApi(final OrderGenerator orderGenerator) {
        this.orderGenerator = orderGenerator;
        random = new Random();
    }

    @GetMapping("orders")
    ResponseEntity<List<Order>> listOrders() {
        final HttpStatus httpStatus = pickStatus();

        if (httpStatus.is2xxSuccessful()) {
            final int numberOfOrders = random.nextInt(20) + 1;
            final List<Order> orders = IntStream.range(0, numberOfOrders)
                    .mapToObj(i -> orderGenerator.randomOrder())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(orders);
        }

        return ResponseEntity.status(httpStatus).build();
    }

    @GetMapping("orders/{orderId}")
    ResponseEntity<Order> getOrder(@PathVariable final String orderId) {
        final HttpStatus httpStatus = pickStatus();

        if (httpStatus.is2xxSuccessful()) {
            return ResponseEntity.ok(orderGenerator.randomOrder(orderId));
        }

        return ResponseEntity.status(httpStatus).build();
    }

    @PostMapping("orders")
    ResponseEntity<Void> postOrder() {
        return ResponseEntity.status(pickStatus()).build();
    }

    @PutMapping("orders/{orderId}")
    ResponseEntity<Void> putOrder(@PathVariable final String orderId) {
        return ResponseEntity.status(pickStatus()).build();
    }

    @DeleteMapping("orders/{orderId}")
    ResponseEntity<Void> deleteOrder(@PathVariable final String orderId) {
        return ResponseEntity.status(pickStatus()).build();
    }

    private HttpStatus pickStatus() {
        final int successPick = random.nextInt(6);

        if (successPick == 0) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (successPick == 1) {
            return HttpStatus.NOT_FOUND;
        }
        if (successPick == 2) {
            return HttpStatus.FORBIDDEN;
        }

        return HttpStatus.OK;
    }
}
