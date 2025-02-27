package com.nur.services;

import com.nur.dto.EmployeeDTO;
import com.nur.entity.Employee;
import com.nur.exceptions.EmployeeNotFoundException;
import com.nur.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getAge(),employeeDTO.getSalary());
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(savedEmployee.getId(),savedEmployee.getName(),savedEmployee.getAge(),savedEmployee.getSalary());
    }

//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeRepository.findAll().stream()
//                .map(employee -> new EmployeeDTO(employee.getId(),employee.getName(),employee.getAge(),employee.getSalary())).toList();
//    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this:: convertToEmployeeDTO).toList();
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(),employee.getName(),employee.getAge(),employee.getSalary());
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getAge(), employee.getSalary());
    }


}