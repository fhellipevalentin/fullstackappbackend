package com.example.fullstackappbackend.service;

import com.example.fullstackappbackend.models.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {

    // definir o recurso que auxiliará com referências de operações que serão executadas com o mongoDB
    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    // construir um metodo, para estabelecer a forma de como a estrutura sequencial se dará
    public Long generateSequence(String seqName) {
        // propriedade definida com a classe DatabaseSequence
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").
                is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert((true)),
                DatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq(): 1;
    }
}
