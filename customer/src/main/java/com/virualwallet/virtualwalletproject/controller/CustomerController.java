package com.virualwallet.virtualwalletproject.controller;

import com.virualwallet.virtualwalletproject.dto.CustomerRequest;
import com.virualwallet.virtualwalletproject.dto.CustomerResponse;
import com.virualwallet.virtualwalletproject.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerRequest request) { service.create(request); }

    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAll() { return service.getAll(); }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest request, @PathVariable Long id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        service.delete(id);
    }


}
