package com.services.sms.smsService.repositories;

import com.services.sms.smsService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by Aishwarya Rokade
 */
@Repository
public interface smsRepo extends JpaRepository<Customer,String> {

    // Query to fetch the customer from database using Name
    @Query("Select c from Customer c WHERE c.name LIKE %?1%")
    Customer getCustomer(@Param("name") String name);

}
