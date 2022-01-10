package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
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

import java.time.LocalDate;

/**
 * This class provides the unit test for the CompensationServiceImpl
 * 
 * @author      Sharvai Patil
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeUrl;
    private String compensationCreateUrl;
    private String compensationReadUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationCreateUrl = "http://localhost:" + port + "/compensation";
        compensationReadUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Peter");
        testEmployee.setLastName("Parker");
        testEmployee.setDepartment("Technology");
        testEmployee.setPosition("Designer");

        // Create checks
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        Compensation argCompensation = new Compensation(createdEmployee, 250000.00, LocalDate.now());
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, argCompensation, Compensation.class).getBody();
        
        assertNotNull(createdCompensation);
        assertCompensationEquivalence(argCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationReadUrl, Compensation.class, createdEmployee.getEmployeeId()).getBody();
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    private void assertCompensationEquivalence(Compensation compensation1, Compensation compensation2){
        assertEmployeeEquivalence(compensation1.getEmployee(), compensation2.getEmployee());
        assertEquals(compensation1.getSalary(), compensation2.getSalary(), 0);
        assertEquals(compensation1.getEffectiveDate(), compensation2.getEffectiveDate());
    }

    private void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
