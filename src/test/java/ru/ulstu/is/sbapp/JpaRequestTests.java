package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Request;
import ru.ulstu.is.sbapp.student.model.Orderr;
import ru.ulstu.is.sbapp.student.service.RequestService;
import ru.ulstu.is.sbapp.student.service.OrderrService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaRequestTests {
    private static final Logger log = LoggerFactory.getLogger(JpaSellerTests.class);

    @Autowired
    private RequestService requestService;
    @Autowired
    private OrderrService orderrService;

    @Test
    void testRequestCreate() {
        requestService.deleteAllRequests();
        final Request request = requestService.addRequest("Имя заявки", "01-01-2017");
        log.info(request.toString());
        Assertions.assertNotNull(request.getId());
    }

    @Test
    void testRequestRead() {
        requestService.deleteAllRequests();
        final Request request = requestService.addRequest("Имя заявки", "01-01-2017");
        log.info(request.toString());
        final Request findRequest = requestService.findRequest(request.getId());
        log.info(findRequest.toString());
        Assertions.assertEquals(request, findRequest);
    }

    @Test
    void testRequestReadNotFound() {
        requestService.deleteAllRequests();
        Assertions.assertThrows(EntityNotFoundException.class, () -> requestService.findRequest(-1L));
    }

    @Test
    void testRequestReadAll() {
        requestService.deleteAllRequests();
        requestService.addRequest("Имя заявки", "01-01-2017");
        requestService.addRequest("Имя заявки", "01-01-2017");
        final List<Request> requests = requestService.findAllRequests();
        log.info(requests.toString());
        Assertions.assertEquals(requests.size(), 2);
    }

    @Test
    void testRequestReadAllEmpty() {
        requestService.deleteAllRequests();
        final List<Request> requests = requestService.findAllRequests();
        log.info(requests.toString());
        Assertions.assertEquals(requests.size(), 0);
    }

    @Test
    void testRequestAddTypeReporting() {
        requestService.deleteAllRequests();
        orderrService.deleteAllOrderrs();
        final Orderr orderr = orderrService.addOrderr("Заказ", "01-01-1970");
        final Request request = requestService.addRequest("Имя заявки", "01-01-2017");
        requestService.addOrderrToRequest(orderr, request);

        log.info(request.toString());
        Assertions.assertEquals(orderr, request.getOrderrs().get(0));
    }
}
