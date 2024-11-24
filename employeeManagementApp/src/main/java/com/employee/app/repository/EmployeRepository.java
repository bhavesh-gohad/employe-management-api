package com.employee.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.app.entity.Employee;

public interface EmployeRepository extends JpaRepository<Employee, Long>
{

}
