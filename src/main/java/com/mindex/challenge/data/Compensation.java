package com.mindex.challenge.data;

import java.time.LocalDate;

/**
 * This class defines the Compensation type
 * @author      Sharvai Patil
 */
public class Compensation {
    private Employee employee;
    private double salary;
    private LocalDate effectiveDate;

    public Compensation() {
    }

    /**
    * Paramterised constructor to initialize employee, salary and effectiveDate
    *
    * @param       employee (type: Employee)
    * @param       salary (type: double)
    * @param       effectiveDate (type: LocalDate)
    * @return      void
    *
    */
    public Compensation(Employee employee, double salary, LocalDate effectiveDate) {
        this.employee = employee;
        this.salary = salary; 
        this.effectiveDate = effectiveDate;
    }

    /**
    * Getter method to return the employee object
    * 
    * @return      Employee
    *
    */
    public Employee getEmployee() {
        return employee;
    }

    /**
    * Setter method to update the employee object
    *
    * @param       employee (type: Employee)
    * @return      void
    */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
    * Getter method to return the salary
    * 
    * @return      double
    *
    */
    public double getSalary() {
        return salary;
    }

    /**
    * Setter method to update the salary 
    *
    * @param       salary (type: double)
    * @return      void
    */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
    * Getter method to return the effective date
    * 
    * @return      LocalDate
    *
    */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
    * Setter method to update the effective date
    *
    * @param       effectiveDate (type: LocalDate)
    * @return      void
    */
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
