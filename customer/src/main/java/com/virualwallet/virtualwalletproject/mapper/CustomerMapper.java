package com.virualwallet.virtualwalletproject.mapper;

import com.virualwallet.virtualwalletproject.dto.CustomerRequest;
import com.virualwallet.virtualwalletproject.dto.CustomerResponse;
import com.virualwallet.virtualwalletproject.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {



//    @Mapping(source = "dniCustomer", target = "dni")
    Customer toCustomerEntity(CustomerRequest request);

    CustomerResponse toCustomerResponse(Customer customer);

    List<Customer> toCustomerEntity(Iterable<CustomerRequest> requests);

    List<CustomerResponse> toCustomerResponse(Iterable<Customer> customers);



}
