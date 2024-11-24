package com.employee.app.service;

import java.util.List;
import java.util.Optional;

import com.employee.app.entity.Employee;

public interface EmployeeService 
{
	public Employee createEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Optional<Employee> getEmployeeById(Long id);
	public Employee updateEmployee(Employee newEmp, Employee oldEmp);
	public void deleteEmpById(Long id);
}
