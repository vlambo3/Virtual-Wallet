package com.virualwallet.virtualwalletproject.repository;

import com.virualwallet.virtualwalletproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT dni FROM Customer c WHERE c.dni = :dni")
    public String findByDni (@Param("dni") String dni);

}
