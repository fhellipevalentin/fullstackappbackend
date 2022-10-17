package com.example.fullstackappbackend.repository;

import com.example.fullstackappbackend.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Long> {
}
