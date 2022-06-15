package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Consignment;
import ru.ulstu.is.sbapp.student.model.Orderr;
import ru.ulstu.is.sbapp.student.service.ConsignmentService;
import ru.ulstu.is.sbapp.student.service.OrderrService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaConsignmentTests {
    private static final Logger log = LoggerFactory.getLogger(JpaSellerTests.class);

    @Autowired
    private ConsignmentService consignmentService;
    @Autowired
    private OrderrService orderrService;

    @Test
    void testConsignmentCreate() {
        consignmentService.deleteAllConsignments();
        final Consignment consignment = consignmentService.addConsignment("Партия");
        log.info(consignment.toString());
        Assertions.assertNotNull(consignment.getId());
    }

    @Test
    void testConsignmentRead() {
        consignmentService.deleteAllConsignments();
        final Consignment consignment = consignmentService.addConsignment("Партия");
        log.info(consignment.toString());
        final Consignment findConsignment = consignmentService.findConsignment(consignment.getId());
        log.info(findConsignment.toString());
        Assertions.assertEquals(consignment, findConsignment);
    }

    @Test
    void testConsignmentReadNotFound() {
        consignmentService.deleteAllConsignments();
        Assertions.assertThrows(EntityNotFoundException.class, () -> consignmentService.findConsignment(-1L));
    }

    @Test
    void testConsignmentReadAll() {
        consignmentService.deleteAllConsignments();
        consignmentService.addConsignment("Партия");
        consignmentService.addConsignment("Партия");
        final List<Consignment> consignments = consignmentService.findAllConsignments();
        log.info(consignments.toString());
        Assertions.assertEquals(consignments.size(), 2);
    }

    @Test
    void testConsignmentReadAllEmpty() {
        consignmentService.deleteAllConsignments();
        final List<Consignment> consignments = consignmentService.findAllConsignments();
        log.info(consignments.toString());
        Assertions.assertEquals(consignments.size(), 0);
    }

    @Test
    void testAddOrderToConsignment() {
        consignmentService.deleteAllConsignments();
        orderrService.deleteAllOrderrs();
        final Orderr orderr = orderrService.addOrderr("Заказ", "01-01-1970");
        final Consignment consignment = consignmentService.addConsignment("Партия");
        consignmentService.addOrderrToConsignment(consignment, orderr);
        log.info(consignment.toString());
        Assertions.assertEquals(orderr, consignment.getOrderrs().get(0));
    }
}
