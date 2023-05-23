package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository er;

	@Override
	public Employee saveEmployee(Employee employee) {
		return er.save(employee);
	}

	@Override
	public List<Employee> getAllEmployess() {
		return er.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employee = er.findById(id);
		 * 
		 * if (employee.isPresent()) { return employee.get(); } else { throw new
		 * ResourceNotFoundException("Employee", "Id", id);}
		 */
		return er.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		Employee existingEmployee = er.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstname(employee.getFirstname());
		existingEmployee.setLastname(employee.getLastname());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to database
		er.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		er.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		er.deleteById(id);
	}

}


 
