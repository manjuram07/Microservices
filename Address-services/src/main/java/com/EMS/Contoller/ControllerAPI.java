package com.EMS.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMS.DTO.AddressDTO;
import com.EMS.Service.AddressService;

@RestController
@RequestMapping(value = "/address-api")
public class ControllerAPI {

	@Autowired
	private AddressService addressService;

@GetMapping("/address/{employeeId}")
public ResponseEntity<AddressDTO> getAddressByEmployeeId(@PathVariable("employeeId") Integer id){

	AddressDTO addressByEmployeeId = addressService.getAddressByEmployeeId(id);

	return new ResponseEntity<AddressDTO>(addressByEmployeeId, HttpStatus.OK);
}
}
