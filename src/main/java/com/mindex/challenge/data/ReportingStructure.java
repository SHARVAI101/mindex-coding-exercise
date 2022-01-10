package com.mindex.challenge.data;

/**
 * This class defines the ReportingStructure type
 * @author      Sharvai Patil
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    /**
    * Paramterised constructor to initialize employee and numberOfReports
    *
    * @param       employee (type: Employee)
    * @param       numberOfReports (type: int)
    * @return      void
    *
    */
    public ReportingStructure(Employee employee, int numberOfReports){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    /**
    * Getter method to return the employee object
    * 
    * @return      Employee
    *
    */
    public Employee getEmployee() {
        return this.employee;
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
    * Getter method to return the number of reports 
    * 
    * @return      int
    *
    */
    public int getNumberOfReports() {
        return this.numberOfReports;
    }

    /**
    * Setter method to update the number of reports
    *
    * @param       numberOfReports (type: int)
    * @return      void
    */
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}