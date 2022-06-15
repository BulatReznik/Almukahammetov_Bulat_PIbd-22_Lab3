package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.student.model.Consignment;
import ru.ulstu.is.sbapp.student.service.ConsignmentService;

import java.util.List;

@RestController
@RequestMapping("/consignment")
public class ConsignmentController {
    private final ConsignmentService consignmentService;

    public ConsignmentController(ConsignmentService consignmentService) {
        this.consignmentService = consignmentService;
    }

    @GetMapping("/{id}")
    public Consignment getConsignment(@PathVariable Long id) {
        return consignmentService.findConsignment(id);
    }

    @GetMapping("/")
    public List<Consignment> getConsignments() {
        return consignmentService.findAllConsignments();
    }

    @PostMapping("/")
    public Consignment createConsignment(@RequestParam("consignmentName") String consignmentName){
        return consignmentService.addConsignment(consignmentName);
    }

    @PatchMapping("/{id}")
    public Consignment updateConsignment(@PathVariable Long id,
                                        @RequestParam("consignmentName") String consignmentName){
        return consignmentService.updateConsignment(id, consignmentName);
    }

    @DeleteMapping("/{id}")
    public Consignment deleteConsignment(@PathVariable Long id) {
        return consignmentService.deleteConsignment(id);
    }
}
