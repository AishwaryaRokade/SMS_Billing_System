package com.services.sms.smsService.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

/**
 * Created by Aishwarya Rokade
 */
@Entity(name="Customer")
public class Customer {

    @Id
    @Column(name="NAME")
    String name;
    @Column(name = "PLAN")
    String plan;
    @Column(name="NO_OF_SMS")
    int noOfSMS;

    @Column(name="PREVIOUS_MONTH_BILL")
    String previousMonthBill;

    @Column(name="LAST_UPDATED_TIME")
    Timestamp lastUpdatedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getNoOfSMS() {
        return noOfSMS;
    }

    public void setNoOfSMS(int noOfSMS) {
        this.noOfSMS = noOfSMS;
    }

    public String getPreviousMonthBill() {
        return previousMonthBill;
    }

    public void setPreviousMonthBill(String previousMonthBill) {
        this.previousMonthBill = previousMonthBill;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", plan='" + plan + '\'' +
                ", noOfSMS=" + noOfSMS +
                ", previousMonthBill='" + previousMonthBill + '\'' +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
