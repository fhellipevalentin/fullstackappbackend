package com.example.fullstackappbackend.models;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document(collection = "database_sequence")
public class DatabaseSequence {
    // diretiva q auxilia nos registros q serao armazenagos na base de dados
    @Id
    private String id; // sequencia alfanumerica gerada pelo mongoDB = automaticamente sera substituida - em tese - pela
    // propriedade @Transient SEQUENCE_NAME
    private Long seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
