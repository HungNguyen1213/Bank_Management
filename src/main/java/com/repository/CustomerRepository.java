package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
}
