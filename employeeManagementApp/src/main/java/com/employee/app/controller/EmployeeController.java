package com.employee.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employee.app.entity.Employee;
import com.employee.app.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) 
	{
		Optional<Employee> empExists = null;

		try 
		{
			empExists = employeeService.getEmployeeById(employee.getEmp_id());

			if (!empExists.isPresent()) 
			{
				employee = employeeService.createEmployee(employee);

				if(employee != null) 
				{
					return new ResponseEntity<>(employee, HttpStatus.CREATED);
				}
			} 
			else 
			{
				return new ResponseEntity<>("Employe already exists", HttpStatus.CONFLICT);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(employee, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllEmployee() 
	{
		List<Employee> empList = null;
		
		try 
		{
			empList = employeeService.getAllEmployees();

			if(empList != null && !empList.isEmpty()) 
			{
				return new ResponseEntity<>(empList, HttpStatus.OK);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("No Employee Details found", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long myId) 
	{
		Optional<Employee> employee = null;

		try 
		{
			employee = employeeService.getEmployeeById(myId);

			if (employee != null && !employee.isEmpty()) 
			{
				return new ResponseEntity<>(employee, HttpStatus.OK);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Employe Not Found", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("id/{myId}")
	public ResponseEntity<?> updateEmploye(@PathVariable Long myId, @RequestBody Employee newEmp) 
	{
		Employee oldEmp = null;

		try 
		{
			oldEmp = employeeService.getEmployeeById(myId).orElse(null);

			if(oldEmp != null && newEmp != null) 
			{
				oldEmp = employeeService.updateEmployee(newEmp, oldEmp);
				return new ResponseEntity<>(oldEmp, HttpStatus.OK);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(oldEmp, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Employe Not Found", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteEmpById(@PathVariable Long myId) 
	{
		Optional<Employee> employee = null;
		
		try 
		{
			employee = employeeService.getEmployeeById(myId);
			
			if(employee != null && !employee.isEmpty())
			{
				employeeService.deleteEmpById(myId);
				return new ResponseEntity<>(employee,HttpStatus.OK);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Employe Not Found", HttpStatus.NOT_FOUND);
	}
}
