package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class provides implementation for the CompensationService interface 
 * and serves as the backend for the Compensation APIs
 * 
 * @author      Sharvai Patil
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    /**
    * create method that receives the compensation object from the api call and constructs the 
    * newly created compensation object as well as stores it in the persistence layer
    *
    * @param       compensation (type: Compensation)
    *
    * @return      Compensation
    *
    */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation with the object [{}]", compensation);

        String employeeId = compensation.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        
        if (employee == null) 
            throw new RuntimeException("Invalid employeeId: " + employeeId);

        Compensation newCompensation = new Compensation(employee, compensation.getSalary(), compensation.getEffectiveDate());

        compensationRepository.insert(newCompensation);

        return compensation;
    }

    /**
    * read method that receives the employeeId and queries the persistence layer and returns
    * the Compensation for the employee with that employeeId
    *
    * @param       employeeId (type: String)
    *
    * @return      Compensation
    *
    */
    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation for employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) 
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        
        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) 
            throw new RuntimeException("Compensation for this employee with id [{}] doesn't exist" + employeeId);

        return compensation;       
    }
}
