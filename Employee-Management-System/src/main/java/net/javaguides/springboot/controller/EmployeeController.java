package net.javaguides.springboot.controller;

import java.util.List;

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

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService es;

	// build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(es.saveEmployee(employee), HttpStatus.CREATED);
	}

	// build get all employees REST API
	@GetMapping()
	public List<Employee> getAllEmployess() {
		return es.getAllEmployess();
	}

	// build get employees by id REST API
	// http://localhost:8080/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeid) {

		return new ResponseEntity<Employee>(es.getEmployeeById(employeeid), HttpStatus.OK);
	}

	// build update employee REST API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long Id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(es.updateEmployee(employee, Id), HttpStatus.OK);

	}

	// build delete employee REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
		es.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}

}
