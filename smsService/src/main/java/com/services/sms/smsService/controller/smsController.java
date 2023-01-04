package com.services.sms.smsService.controller;

import com.services.sms.smsService.model.Customer;
import com.services.sms.smsService.repositories.smsRepo;
import com.services.sms.smsService.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


/**
 * Created by Aishwarya Rokade
 */

@Component
@Controller
@RestController
@RequestMapping("/smsService")
public class smsController {
    @Autowired
    smsRepo SmsRepo;
    @Autowired
    private BillingService billingService;

    @PostMapping(value="/Add")  // add new customer
    ResponseEntity<String> AddCustomer(@RequestParam(name = "CustomerName") String name, @RequestParam(name = "Plan",defaultValue = "Basic") String plan)
    {
        Customer newCust = new Customer();
        newCust.setName(name);
        newCust.setPlan(plan);
        newCust.setNoOfSMS(0);
        newCust.setPreviousMonthBill("0");
        newCust.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        SmsRepo.save(newCust);

        return new ResponseEntity<>("New Customer Added Successfully", HttpStatus.OK);

    }
    @PostMapping(value="/SMS")   // Method to Send SMS
    ResponseEntity<String> sendSMS(@RequestParam(name = "CustomerName") String name,@RequestBody() String message)
    {
        int count=0;
        double price;
        Customer cust = SmsRepo.getCustomer(name);
        int dbMon = cust.getLastUpdatedTime().getMonth();  // last updated month from database
        int currentMon=new Timestamp(System.currentTimeMillis()).getMonth();  // Current mont from system
        if(dbMon==currentMon)  // check is both are same, then increase the count of SMS
        {
            count= cust.getNoOfSMS()+1;
            cust.setNoOfSMS(count);
            price = billingService.smsPrice(cust);
        }
        else {  // move the previous month bill
            double total=0;
            total = billingService.generteBill(cust);
            System.out.println("Total Bill" + total +"USD");
            cust.setPreviousMonthBill(String.valueOf(total));
            cust.setNoOfSMS(1);
            price= 0.001;

        }
        cust.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        SmsRepo.save(cust);
        return new ResponseEntity<>("Message Sent Successfull from Customer:" + name + " ,with price of =" + price +" USD per SMS", HttpStatus.OK);

    }
    @GetMapping(value="/bill")   // get the Bill for current month
    ResponseEntity<String> getbill(@RequestParam(name = "CustomerName") String name)
    {
        System.out.println("Inside get bill");
        Customer cust = SmsRepo.getCustomer(name);
        double total=0;
        total = billingService.generteBill(cust);
        System.out.println("Total Bill" + total +"USD");
        return new ResponseEntity<>("Current Month SMS bill =" + total + " USD", HttpStatus.OK);
    }
}
