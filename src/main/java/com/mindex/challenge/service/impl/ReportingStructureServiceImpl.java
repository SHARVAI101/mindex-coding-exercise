package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This class provides implementation for the ReportingStructureService interface 
 * and serves as the backend for the ReportingStructure APIs
 * 
 * @author      Sharvai Patil
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
    * read method that receives the employeeId and queries the persistence layer and returns
    * the ReportingStructure for the employee with that employeeId
    *
    * @param       employeeId (type: String)
    *
    * @return      ReportingStructure
    *
    */
    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading ReportingStructure for employee with id [{}]", employeeId);

        // initializing an empty ReportingStructure object
        ReportingStructure reportingStructure = new ReportingStructure();

        // querying to check if an employee with id equal to employeeId exists
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            LOG.debug("Employee id [{}] is invalid", employeeId);

            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        else{
            int totalNumberOfReports = calcNumberOfReports(employeeId);

            reportingStructure.setEmployee(employee);
            reportingStructure.setNumberOfReports(totalNumberOfReports);
        }

        return reportingStructure;
    }


    /**
    * Helper method that finds the number of direct reports under an employee with the id employeeId
    * using Depth First Search strategy
    *
    * @param       employeeId (type: String)
    *
    * @return      int
    *
    */
    private int calcNumberOfReports(String employeeId){
        LOG.debug("Calculating number of reports for employee with id [{}]", employeeId);

        int totalNumberOfReports = 0;

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        List<Employee> directReports = employee.getDirectReports();
        if(directReports!=null){            
            for(int i=0; i<directReports.size(); i++){
                totalNumberOfReports += 1 + calcNumberOfReports(directReports.get(i).getEmployeeId());
            }
        }

        return totalNumberOfReports;
    }

}
