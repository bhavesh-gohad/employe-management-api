package com.employee.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.entity.Employee;
import com.employee.app.repository.EmployeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeRepository employeRepository;

	@Override
	public Employee createEmployee(Employee employee) 
	{
		return employeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		return employeRepository.findAll();
	}
	
	@Override
	public Optional<Employee> getEmployeeById(Long id) 
	{
		return employeRepository.findById(id);
	}

	@Override
	public Employee updateEmployee(Employee newEmp,Employee oldEmp) 
	{
		oldEmp.setFname(newEmp.getFname() != null && !newEmp.getFname().isEmpty() ? newEmp.getFname() : oldEmp.getFname());
		oldEmp.setLname(newEmp.getLname() != null && !newEmp.getLname().isEmpty() ? newEmp.getLname() : oldEmp.getLname());
		oldEmp.setMob_no(newEmp.getMob_no() != null && !newEmp.getMob_no().isEmpty() ? newEmp.getMob_no() : oldEmp.getMob_no());
		
		employeRepository.save(oldEmp);	

		return oldEmp;
	}
	
	@Override
	public void deleteEmpById(Long id) 
	{
		employeRepository.deleteById(id);
	}
}
