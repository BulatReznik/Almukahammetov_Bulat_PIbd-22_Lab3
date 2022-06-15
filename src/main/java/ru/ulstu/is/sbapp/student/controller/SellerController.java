package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.ulstu.is.sbapp.student.model.Seller;
import ru.ulstu.is.sbapp.student.service.SellerService;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{id}")
    public Seller getSeller(@PathVariable Long id) {
        return sellerService.findSeller(id);
    }

    @GetMapping("/")
    public List<Seller> getSellers() {
        return sellerService.findAllSellers();
    }

    @PostMapping("/")
    public Seller createSeller(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login){
        return sellerService.addSeller(firstName, lastName, login);
    }

    @PatchMapping("/{id}")
    public Seller updateSeller(@PathVariable Long id,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login){
        return sellerService.updateSeller(id, firstName, lastName, login);
    }

    @DeleteMapping("/{id}")
    public Seller deleteSeller(@PathVariable Long id) {
        return sellerService.deleteSeller(id);
    }
}
