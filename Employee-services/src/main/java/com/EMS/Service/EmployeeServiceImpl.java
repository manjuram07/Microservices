package com.EMS.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.EMS.EmployeeServicesApplication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

//import com.EMS.AddressClient;
import com.EMS.DTO.AddressDTO;
import com.EMS.DTO.EmployeeDTO;
import com.EMS.Entity.EmployeeEntity;
import com.EMS.Repository.EmployeeRepository;
import org.springframework.web.client.RestTemplate;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

//	@Autowired
//	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient balancerClient;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;



	public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}

//	@Autowired
//	private AddressClient addressClient;
	
	@Autowired
	private ModelMapper modelMapper;
	
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public EmployeeDTO getEmployee(Integer id) {
		
		EmployeeServicesApplication.logger.info("Fetching the database started....");

		// Retriving Id from database
		Optional<EmployeeEntity> employee = employeeRepository.findById(id);

		// converting Entity to DTO object
		EmployeeDTO dto = modelMapper.map(employee.get(), EmployeeDTO.class);
		
		// calling AddressClient class for Address url or DTO
//		AddressDTO addressDTO = addressClient.getAddressByEmployeeId(id);
		
		AddressDTO addressDTO = getAddressByEmployeeId(id);
		
		dto.setAddress(addressDTO);
		
		EmployeeServicesApplication.logger.info("Retried the data from database...");

		return dto;
		
	}

	private AddressDTO getAddressByEmployeeId(Integer id) {
//		List<ServiceInstance> instances = discoveryClient.getInstances("Address-services");
//		ServiceInstance serviceInstance = instances.get(0);
//		String uri = serviceInstance.getUri().toString();
		
//		ServiceInstance serviceInstance = balancerClient.choose("Address-services");
//		String uri = serviceInstance.getUri().toString();
//
//		System.out.println("uri----------------------"+ uri);
//
//		return restTemplate.getForObject(uri+"/address-api/address/{id}", AddressDTO.class, id);

		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("addressServiceCB");
		// Use Eureka service name, not hardcoded host/port
		String url = "http://address-service/address/" + id;

		return circuitBreaker.run(
				() -> restTemplate.getForObject(url, AddressDTO.class),
				throwable -> fallbackAddress(id, throwable)
		);

	}

	public AddressDTO fallbackAddress(Integer empId, Throwable t) {
		AddressDTO fallback = new AddressDTO();
		fallback.setMessage("Address service unavailable for employee " + empId + ". (Fallback response)");
		// Set other default values as needed
		return fallback;
	}

	@Override
	public List<EmployeeDTO> geAllEmployees() {
		List<EmployeeEntity> employees = employeeRepository.findAll();

		List<EmployeeDTO> list = employees.stream()
				 						  .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
				 						  .collect(Collectors.toList());

		return list;
    }

}
