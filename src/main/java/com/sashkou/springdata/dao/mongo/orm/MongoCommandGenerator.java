package com.sashkou.springdata.dao.mongo.orm;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

@Component
public class MongoCommandGenerator {

    public String generateFindAllCommand(String collectionName) {
        final DBObject command = new BasicDBObject();
        command.put("find", collectionName);

        return command.toString();
    }
}
