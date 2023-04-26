package com.virualwallet.virtualwalletproject.service.impl;

import com.virualwallet.virtualwalletproject.dto.CustomerRequest;
import com.virualwallet.virtualwalletproject.dto.CustomerResponse;
import com.virualwallet.virtualwalletproject.exception.AlreadyExistsException;
import com.virualwallet.virtualwalletproject.exception.NotFoundException;
import com.virualwallet.virtualwalletproject.exception.UnableToUpdateEntityException;
import com.virualwallet.virtualwalletproject.mapper.CustomerMapper;
import com.virualwallet.virtualwalletproject.model.Customer;
import com.virualwallet.virtualwalletproject.repository.CustomerRepository;
import com.virualwallet.virtualwalletproject.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private MessageSource messageSource;

    public void create(CustomerRequest request) {

        List<Customer> customers = repository.findAll();

        customers.forEach(customer -> {
            if(repository.findByDni(customer.getDni()).equals(request.dni())) {
                throw new AlreadyExistsException(
                        messageSource.getMessage("user-already-registered", null, Locale.US)
                );
            }
        });

        customers.forEach(customer -> {
            if(customer.getAlias().equalsIgnoreCase(request.alias())) {
                throw new AlreadyExistsException(
                        messageSource.getMessage("alias-is-in-use", null, Locale.US)
                );
            }
        });

        Customer customer = repository.save(mapper.toCustomerEntity(request));
        customer.setAlias(customer.getAlias().concat(".wallet"));

        customer.setCvu(getNewCvu().toString());

        repository.save(customer);
    }

    public List<CustomerResponse> getAll() {
        List<Customer> customers = repository.findAll();
        return mapper.toCustomerResponse(customers);
    }

    private Long getNewCvu() {
        return Math.abs(ThreadLocalRandom.current().nextLong());
    }

    public CustomerResponse update(CustomerRequest request, Long id) {
        Customer customer = getCustomerById(id);

        try {
            mapper.toCustomerEntity(request);
            customer.setPhone(request.phone());
            customer.setName(request.name());
            customer.setLastname(request.lastname());
            repository.save(customer);
            return mapper.toCustomerResponse(customer);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(
                    messageSource.getMessage("unable-to-update-customer", new Object[] {id}, Locale.US));
        }

    }

    private Customer getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new NotFoundException(
                        messageSource.getMessage("customer-not-found", new Object[] {id}, Locale.US))
        );
    }


    public void delete(Long id) {
        Customer customer = getCustomerById(id);
        try {
            customer.setDeleted(Boolean.TRUE);
            repository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(
                    messageSource.getMessage("customer-not-found", new Object[] {id}, Locale.US));
        }
    }
}
