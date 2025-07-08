package com.EMS.Contoller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMS.DTO.EmployeeDTO;
import com.EMS.Service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(value = "/employee-api")
public class ControllerAPI {

	@Autowired
	private EmployeeService employeeService;


	public static final String Employee_service = "Employee-services";

	@GetMapping("/login")
	public String loginPage() {
		return "login.html"; // Returns the login.html page
	}

//	@GetMapping("/homepage")
//	public String homepage(Authentication authentication, Model model) {
//		model.addAttribute("username", authentication.getName());
//		return "homepage"; // Returns the homepage.html page
//	}
	
	@GetMapping("/employee/{id}")
	@CircuitBreaker(name = "Employee-services", fallbackMethod = "fallbackMethod")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer id){
		
		EmployeeDTO employee = employeeService.getEmployee(id);
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
	}

	public ResponseEntity<EmployeeDTO> fallbackMethod(EmployeeDTO employeeDTO){
//		EmployeeDTO fallbackEmployee = new EmployeeDTO();
//		fallbackEmployee.setMessage("Service is currently unavailable. This is a fallback response.");
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}




	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){

		List<EmployeeDTO> employees = employeeService.geAllEmployees();

		return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
	}
}
