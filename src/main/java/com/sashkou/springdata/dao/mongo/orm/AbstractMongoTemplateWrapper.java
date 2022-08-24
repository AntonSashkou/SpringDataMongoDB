package com.sashkou.springdata.dao.mongo.orm;

import com.sashkou.springdata.config.MongoConfig;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMongoTemplateWrapper {

    protected abstract MongoConfig getMongoConfig();

    private MongoTemplate mongoTemplate() {
        return new MongoTemplate(getMongoConfig().mongoClient(), getMongoConfig().getDatabase());
    }

    public List<String> execute(String command) {
        return mongoTemplate()
                .executeCommand(command)
                .toBsonDocument()
                .getDocument("cursor")
                .getArray("firstBatch")
                .stream()
                .map(bsonValue -> bsonValue.asDocument().toJson())
                .collect(Collectors.toList());
    }
}
