package ru.ulstu.is.sbapp;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.student.model.Seller;
import ru.ulstu.is.sbapp.student.service.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@SpringBootTest
public class JpaSellerTests {
    private static final Logger log = LoggerFactory.getLogger(JpaSellerTests.class);

    @Autowired
    private SellerService sellerService;

    @Test
    void testSellerCreate() {
        sellerService.deleteAllSellers();
        final Seller seller = sellerService.addSeller("Имя", "Фамиля", "login");
        log.info(seller.toString());
        Assertions.assertNotNull(seller.getId());
    }

    @Test
    void testSellerRead() {
        sellerService.deleteAllSellers();
        final Seller seller = sellerService.addSeller("Имя", "Фамиля", "login");
        log.info(seller.toString());
        final Seller findSeller = sellerService.findSeller(seller.getId());
        log.info(findSeller.toString());
        Assertions.assertEquals(seller, findSeller);
    }

    @Test
    void testSellerReadNotFound() {
        sellerService.deleteAllSellers();
        Assertions.assertThrows(EntityNotFoundException.class, () -> sellerService.findSeller(-1L));
    }

    @Test
    void testSellerReadAll() {
        sellerService.deleteAllSellers();
        sellerService.addSeller("Имя", "Фамиля", "login");
        sellerService.addSeller("Имя", "Фамиля", "login");
        final List<Seller> seller = sellerService.findAllSellers();
        log.info(seller.toString());
        Assertions.assertEquals(seller.size(), 2);
    }

    @Test
    void testSellerReadAllEmpty() {
        sellerService.deleteAllSellers();
        final List<Seller> sellers = sellerService.findAllSellers();
        log.info(sellers.toString());
        Assertions.assertEquals(sellers.size(), 0);
    }
}
