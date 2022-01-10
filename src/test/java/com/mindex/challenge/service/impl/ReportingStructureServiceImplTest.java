package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.ArrayList;

/**
 * This class provides the unit test for the ReportingStructureServiceImpl
 * 
 * @author      Sharvai Patil
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String reportingStructureUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
        // Read checks

        // creating a new employee with 2 direct reports each with 0 direct reports of their own
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Mary");
        testEmployee.setLastName("Jane");
        testEmployee.setDepartment("Data Analytics");
        testEmployee.setPosition("Developer 2");

        Employee directReport1 = new Employee();
        directReport1.setFirstName("Jordan");
        Employee createdDirectReport1 = restTemplate.postForEntity(employeeUrl, directReport1, Employee.class).getBody();

        Employee directReport2 = new Employee();
        directReport2.setFirstName("Maya");
        Employee createdDirectReport2 = restTemplate.postForEntity(employeeUrl, directReport2, Employee.class).getBody();

        // adding the 2 employees to the testEmployee's directReports' list
        List<Employee> directReports = new ArrayList<>();
        directReports.add(createdDirectReport1);
        directReports.add(createdDirectReport2);

        testEmployee.setDirectReports(directReports);

        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdEmployee.getEmployeeId()).getBody();
        
        assertEquals(readReportingStructure.getNumberOfReports(), 2);
    }
}
