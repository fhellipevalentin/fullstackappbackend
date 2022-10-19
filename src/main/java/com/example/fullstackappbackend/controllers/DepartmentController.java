package com.example.fullstackappbackend.controllers;

import com.example.fullstackappbackend.exception.ResourceNotFoundException;
import com.example.fullstackappbackend.models.Department;
import com.example.fullstackappbackend.repository.DepartmentRepository;
import com.example.fullstackappbackend.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable(value = "id") Long departmentId )
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado: " + departmentId));
        return ResponseEntity.ok().body(department);
    }
    @PostMapping("/departments")
    public Department createDepartment(@Valid @RequestBody Department department) {
        department.setId(sequenceGeneratorService.generateSequence(Department.SEQUENCE_NAME));
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Long departmentId,
                                                   @Valid @RequestBody Department departmentDetails)
            throws ResourceNotFoundException{
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado: " + departmentId));
        // manipular valores a partir das propriedades definidas no model
        department.setCategoria(departmentDetails.getCategoria());
        final Department updateDepartment = departmentRepository.save(department);

        return ResponseEntity.ok(updateDepartment);
    }
    @DeleteMapping("/departments/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado: " + departmentId));
        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("excluído", Boolean.TRUE);
        return response;
    }

    @Override
    public String toString() {
        return "DepartmentController{" +
                "departmentRepository=" + departmentRepository +
                ", sequenceGeneratorService=" + sequenceGeneratorService +
                '}';
    }
}
