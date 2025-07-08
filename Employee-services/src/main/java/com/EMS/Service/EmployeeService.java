package com.EMS.Service;

import com.EMS.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

	public EmployeeDTO getEmployee(Integer id);

	public List<EmployeeDTO> geAllEmployees();
}
