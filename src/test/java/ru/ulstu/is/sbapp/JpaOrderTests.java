package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Consignment;
import ru.ulstu.is.sbapp.student.model.Orderr;
import ru.ulstu.is.sbapp.student.model.Request;
import ru.ulstu.is.sbapp.student.service.ConsignmentService;
import ru.ulstu.is.sbapp.student.service.OrderrService;
import ru.ulstu.is.sbapp.student.service.RequestService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaOrderTests {
    private static final Logger log = LoggerFactory.getLogger(JpaSellerTests.class);

    @Autowired
    private OrderrService orderrService;
    @Autowired
    private ConsignmentService consignmentService;
    @Autowired
    private RequestService requestService;

    @Test
    void testOrderrCreate() {
        orderrService.deleteAllOrderrs();
        final Orderr orderr = orderrService.addOrderr("Заказ", "01-01-1970");
        log.info(orderr.toString());
        Assertions.assertNotNull(orderr.getId());
    }

    @Test
    void testOrderrRead() {
        orderrService.deleteAllOrderrs();
        final Orderr orderr = orderrService.addOrderr("Заказ", "01-01-1970");
        log.info(orderr.toString());
        final Orderr findOrderr = orderrService.findOrderr(orderr.getId());
        log.info(orderr.toString());
        Assertions.assertEquals(orderr, findOrderr);
    }

    @Test
    void testOrderrReadNotFound() {
        orderrService.deleteAllOrderrs();
        Assertions.assertThrows(EntityNotFoundException.class, () -> orderrService.findOrderr(-1L));
    }

    @Test
    void testOrderrReadAll() {
        orderrService.deleteAllOrderrs();
        orderrService.addOrderr("Заказ", "01-01-1970");
        orderrService.addOrderr("Заказ", "01-01-1970");
        final List<Orderr> orderrs = orderrService.findAllOrderrs();
        log.info(orderrs.toString());
        Assertions.assertEquals(orderrs.size(), 2);
    }

    @Test
    void testOrderrReadAllEmpty() {
        orderrService.deleteAllOrderrs();
        final List<Orderr> orderrs = orderrService.findAllOrderrs();
        log.info(orderrs.toString());
        Assertions.assertEquals(orderrs.size(), 0);
    }

    @Test
    void testAddRequestToOrder() {
        requestService.deleteAllRequests();
        orderrService.deleteAllOrderrs();
        final Orderr orderr = orderrService.addOrderr("Заказ", "01-01-1970");
        final Request request = requestService.addRequest("Имя заявки", "01-01-2017");
        orderrService.addRequestToOrderr(request, orderr);
        log.info(orderr.toString());
        Assertions.assertEquals(request, orderr.getRequests().get(0));
    }

    @Test
    void testAddConsignmentToOrder() {
        consignmentService.deleteAllConsignments();
        orderrService.deleteAllOrderrs();
        final Orderr orderr = orderrService.addOrderr("Name", "01-01-1970");
        final Consignment consignment = consignmentService.addConsignment("Партия");
        orderrService.addConsignmentToOrders(consignment, orderr);
        log.info(orderr.toString());
        Assertions.assertEquals(consignment, orderr.getConsignments().get(0));
    }
}
