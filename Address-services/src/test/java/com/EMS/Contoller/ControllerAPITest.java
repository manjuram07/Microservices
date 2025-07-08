package com.EMS.Contoller;

import com.EMS.DTO.AddressDTO;
import com.EMS.Service.AddressService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerAPI.class)
class ControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService addressService;

    AddressDTO addressDTO;

    List<AddressDTO> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        addressDTO = new AddressDTO();
        addressDTO.setAddress1("davanagere");
        addressDTO.setAddress2("Mysore");
        addressDTO.setId(1);
        addressDTO.setState("karnataka");
        addressDTO.setZip(570016);

        list.add(addressDTO);
    }

    @Test
    void getAddressByEmployeeId_test() throws Exception {
        Mockito.when(addressService.getAddressByEmployeeId(1))
                           .thenReturn(addressDTO);


        this.mockMvc.perform(get("/address-api/address/"+addressDTO.getId()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json("{id:1,address1:davanagere,address2:Mysore,zip:570016,state:karnataka}"));

        verify(addressService).getAddressByEmployeeId(1);

    }

    @Test
    void NotFoundException_Test() throws Exception {
        Integer nonExistentId = 999;

        when(addressService.getAddressByEmployeeId(nonExistentId))
                           .thenThrow(new Exception("Resource with ID " + nonExistentId + " not found."));


        this.mockMvc.perform(get("/address-api/address/"+nonExistentId))
//                  .andDo(print())
                    .andExpect(status().isNotFound());

        verify(addressService).getAddressByEmployeeId(nonExistentId);
    }

    @AfterEach
    void tearDown() {
    }


}