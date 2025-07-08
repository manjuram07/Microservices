package com.EMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EMS.Entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{

}
