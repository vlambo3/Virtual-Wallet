package com.virualwallet.virtualwalletproject.service;

import com.virualwallet.virtualwalletproject.dto.CustomerRequest;
import com.virualwallet.virtualwalletproject.dto.CustomerResponse;

import java.util.List;

public interface ICustomerService {

    void create(CustomerRequest request);

    List<CustomerResponse> getAll();

    CustomerResponse update(CustomerRequest request, Long id);

    void delete(Long id);

}
