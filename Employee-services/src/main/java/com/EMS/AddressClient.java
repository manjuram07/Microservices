package com.EMS;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.EMS.DTO.AddressDTO;

//@FeignClient(name="address-service", url = "http://localhost:8082/address-api")
//public interface AddressClient {
//
//	@GetMapping("/address/{id}")
//	public AddressDTO getAddressByEmployeeId(@PathVariable Integer id);
//
//}
