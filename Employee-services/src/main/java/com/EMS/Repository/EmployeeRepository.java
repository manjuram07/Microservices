package com.EMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EMS.Entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

}
