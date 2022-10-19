package com.example.fullstackappbackend.repository;

import com.example.fullstackappbackend.models.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, Long> {
}
