package com.services.sms.smsService.services;

import com.services.sms.smsService.model.Customer;
import org.springframework.stereotype.Service;

/**
 * Created by Aishwarya Rokade
 */

@Service
public class BillingService {

    public double generteBill(Customer cust)  // calculate the amount to be paid for current month based on plans
    {
        double total=0;
        double count=cust.getNoOfSMS();
        System.out.println("Customer details:" +  cust.toString());
        if(cust.getPlan().equalsIgnoreCase("Gold"))
        {
            if(count > 100000)   // Offer applied for gold plan customer for above 100k SMS
            {

                count = (count - 100000)*  0.0005;  // Discount amount for above 100k
                total= (98999 * 0.003) + count;  // 1st 1000 SMS are free for Gold, next 1001 to 100k SMS cost 0.003 + discount cost
            }
            else if (count>1000) {   // SMS after 1000 @ cost 0.003
                total=(count - 1000) * 0.003;

            }
            else {    //Gold Plan, 1000 Free SMS
                total =0;
            }

        } else if (cust.getPlan().equalsIgnoreCase("Silver")) {
            if(count > 100)
            {
                total=(count-100) * 0.002;
            }
            else { //Free SMS as per silver Plan below 100
                total=0;
            }

        }
        else   // basic plan calculation
        {
            total=count * 0.001;
        }


        return total;
    }

    public double smsPrice(Customer cust)
    {
        int count = cust.getNoOfSMS();
        double price;
        if(cust.getPlan().equalsIgnoreCase("Gold"))
        {
            if(count>1000)
            {
                price = 0.003;
            }
            else {
                price=0;
            }
        }
        else if(cust.getPlan().equalsIgnoreCase("Silver"))
        {
            if(count>100)
            {
                price = 0.002;
            }
            else {
                price=0;
            }
        }
        else {
            price=0.001;
        }
        return price;
    }
}
