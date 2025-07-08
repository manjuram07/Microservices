package com.EMS;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.EMS.Contoller.ControllerAPI;
import com.EMS.DTO.AddressDTO;
import com.EMS.DTO.EmployeeDTO;
import com.EMS.Service.EmployeeServiceImpl;

@WebMvcTest(ControllerAPI.class)
class ControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private ControllerAPI controllerAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployee_Success() throws Exception {
        // Arrange
        Integer employeeId = 1;
        EmployeeDTO mockEmployee = getEmployeeDTO(employeeId);

        when(employeeService.getEmployee(employeeId)).thenReturn(mockEmployee);

        // Act & Assert
        mockMvc.perform(get("/employee-api/employee/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(employeeId))
                .andExpect((ResultMatcher) jsonPath("$.name").value("raam"));

        verify(employeeService, times(1)).getEmployee(employeeId);
    }

    private static EmployeeDTO getEmployeeDTO(Integer employeeId) {
        EmployeeDTO mockEmployee = new EmployeeDTO();
        AddressDTO mockAddress = new AddressDTO();

        mockEmployee.setId(employeeId);
        mockEmployee.setName("raam");
        mockEmployee.setEmail("raam@gmail.com");

        mockAddress.setAddress1("davanagere");
        mockAddress.setId(employeeId);
        mockAddress.setAddress2("Mysuru");
        mockAddress.setState("karnataka");
        mockAddress.setZip(570016);

        mockEmployee.setAddress(mockAddress);
        return mockEmployee;
    }

    @Test
    void testGetEmployee_NotFound() throws Exception {
        // Arrange
        Integer employeeId = 1;

        when(employeeService.getEmployee(employeeId)).thenThrow(new RuntimeException("Employee not found"));

        // Act & Assert
        mockMvc.perform(get("/api/employee/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Employee not found"));

        verify(employeeService, times(1)).getEmployee(employeeId);
    }
}