package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.student.model.Request;
import ru.ulstu.is.sbapp.student.service.RequestService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable Long id) {
        return requestService.findRequest(id);
    }

    @GetMapping("/")
    public List<Request> getRequests() {
        return requestService.findAllRequests();
    }

    @PostMapping("/")
    public Request createRequest(@RequestParam("requestName") String requestName,
                                  @RequestParam("requestDate") String requestDate){
        return requestService.addRequest(requestName, requestDate);
    }

    @PatchMapping("/{id}")
    public Request updateRequest(@PathVariable Long id,
                                   @RequestParam("requestName") String requestName,
                                   @RequestParam("requestDate") String requestDate){
        return requestService.updateRequest(id, requestName, requestDate);
    }

    @DeleteMapping("/{id}")
    public Request deleteRequest(@PathVariable Long id) {
        return requestService.deleteRequest(id);
    }
}
