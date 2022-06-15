package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.student.model.Orderr;
import ru.ulstu.is.sbapp.student.service.OrderrService;

import java.util.List;

@RestController
@RequestMapping("/orderr")
public class OrderrController {
    private final OrderrService orderrService;

    public OrderrController(OrderrService orderrService) {
        this.orderrService = orderrService;
    }

    @GetMapping("/{id}")
    public Orderr getOrderr(@PathVariable Long id) {
        return orderrService.findOrderr(id);
    }

    @GetMapping("/")
    public List<Orderr> getOrderrs() {
        return orderrService.findAllOrderrs();
    }

    @PostMapping("/")
    public Orderr createOrderr(@RequestParam("orderrName") String orderrName,
                                 @RequestParam("orderrDate") String orderrDate){
        return orderrService.addOrderr(orderrName, orderrDate);
    }

    @PatchMapping("/{id}")
    public Orderr updateOrderr(@PathVariable Long id,
                                 @RequestParam("orderrName") String orderrName,
                                 @RequestParam("orderrDate") String orderrDate){
        return orderrService.updateOrderr(id, orderrName, orderrDate);
    }

    @DeleteMapping("/{id}")
    public Orderr deleteOrderr(@PathVariable Long id) {
        return orderrService.deleteOrderr(id);
    }
}
