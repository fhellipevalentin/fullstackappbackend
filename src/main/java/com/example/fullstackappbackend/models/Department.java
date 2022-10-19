package com.example.fullstackappbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection="Department")
public class Department {
    @Transient
    public static final String SEQUENCE_NAME = "department_sequence";
    @Id
    private Long id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique = true)
    private String categoria;

    public Department(Long id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
