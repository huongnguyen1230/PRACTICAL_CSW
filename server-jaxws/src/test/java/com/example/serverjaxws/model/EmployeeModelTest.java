package com.example.serverjaxws.model;

import com.example.serverjaxws.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeModelTest {

    private EmployeeModel employeeModel;

    @BeforeEach
    void setUp() {
        employeeModel = new EmployeeModel();
    }

    @Test
    void save() throws SQLException {
        Employee employee = new Employee("Nguyen A", 30000, 1);
        Employee insertedEmployee = employeeModel.save(employee);
        assertThat(insertedEmployee.getId()).isNotEqualTo(0);
    }

    @Test
    void findAll() throws SQLException {
        Employee employee = new Employee("Nguyen B", 10000, 1);
        employeeModel.save(employee);
        List<Employee> productList = employeeModel.findAll();
        assertThat(productList.size()).isGreaterThan(0);
    }

    @Test
    void findById() throws SQLException {
        Employee employee = new Employee("Nguyen C", 15000, 1);
        Employee insertedEmployee = employeeModel.save(employee);
        Employee foundEmployee = employeeModel.findById(insertedEmployee.getId());
        assertThat(foundEmployee).isNotNull();
    }

    @Test
    void update() throws SQLException {
        Employee employee = new Employee("Nguyen A", 30000, 1);
        Employee insertedEmployee = employeeModel.save(employee);
        Employee updateEmployee = new Employee("Nguyen D", 25000, 0);
        Employee updatedEmployee = employeeModel.update(insertedEmployee.getId(), updateEmployee);
        assertThat(updatedEmployee).isEqualToComparingFieldByFieldRecursively(updateEmployee);
    }
}