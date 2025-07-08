package com.EMS.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EMS.DTO.AddressDTO;
import com.EMS.Entity.AddressEntity;
import com.EMS.Repository.AddressRepository;

@Service(value = "addressService")
public class AddressServiceImpl implements AddressService{

	private AddressRepository addressRepository;

	public AddressServiceImpl(final AddressRepository addressRepository){
		this.addressRepository = addressRepository;
	}
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AddressDTO getAddressByEmployeeId(Integer id) {
		
		Optional<AddressEntity> address = addressRepository.findById(id);
		AddressDTO map = modelMapper.map(address, AddressDTO.class);
		
		return map;
	}

}
