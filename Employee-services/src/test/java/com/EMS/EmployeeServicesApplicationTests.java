package com.EMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.EMS.DTO.AddressDTO;
import com.EMS.DTO.EmployeeDTO;
import com.EMS.Entity.EmployeeEntity;
import com.EMS.Repository.EmployeeRepository;
import com.EMS.Service.EmployeeServiceImpl;

@SpringBootTest
class EmployeeServicesApplicationTests {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private ModelMapper modelMapper;

//	@Mock
//	private AddressClient addressClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

//	@Test
//	public void testGetEmployee() {
//
//		EmployeeEntity mockEntity = new EmployeeEntity();
//
//		Integer id = 1;
//		mockEntity.setId(id);
//		mockEntity.setName("raam");
//		mockEntity.setEmail("raam@gmail.com");
//
//		EmployeeDTO mockDto = new EmployeeDTO();
//		
//		mockDto.setId(id);
//		mockDto.setName("raam");
//		mockDto.setEmail("raam@gmail.com");
//
//		AddressDTO mockAddress = new AddressDTO();
//		mockAddress.setId(id);
//		mockAddress.setAddress1("davanagere");
//		mockAddress.setAddress2("Mysuru");
//		mockAddress.setZip(570016);
//		mockAddress.setState("karnataka");
//
//		when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEntity));
//		when(modelMapper.map(mockEntity, EmployeeDTO.class)).thenReturn(mockDto);
//		when(addressClient.getAddressByEmployeeId(id)).thenReturn(mockAddress);
//
//		EmployeeDTO result = employeeService.getEmployee(id);
//
//		assertNotNull(result);
//		assertEquals(id, result.getId());
//		assertEquals("raam", result.getName());
//		assertNotNull(result.getAddress());
//		assertEquals("davanagere", result.getAddress().getAddress1());
//		assertEquals("Mysuru", result.getAddress().getAddress2());
//
//		verify(employeeRepository, times(1)).findById(id);
//		verify(modelMapper, times(1)).map(mockEntity, EmployeeDTO.class);
//		verify(addressClient, times(1)).getAddressByEmployeeId(id);
//
//	}
//
//	@Test
//	public void testGetEmployee_NotFound() {
//
//		// Arrange
//		Integer employeeId = 1;
//
//		when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
//
//		// Act & Assert
//		assertThrows(RuntimeException.class, () -> employeeService.getEmployee(employeeId));
//
//		verify(employeeRepository, times(1)).findById(employeeId);
//		verifyNoInteractions(modelMapper, addressClient);
//
//	}
}
