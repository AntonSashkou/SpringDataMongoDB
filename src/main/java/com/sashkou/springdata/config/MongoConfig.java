package com.sashkou.springdata.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.Getter;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.concurrent.TimeUnit;

@Getter
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.db.host}")
    private String host;

    @Value("${mongodb.db.port}")
    private String port;

    @Value("${mongodb.db.name}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString(String.format("mongodb://%s:%s/%s", host, port, database));
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        createExpireAtIndex(mongoClient);

        return mongoClient;
    }

    private void createExpireAtIndex(MongoClient mongoClient) {
        MongoCollection<Document> usersCollection = mongoClient.getDatabase(database).getCollection("users");
        usersCollection.createIndex(Indexes.ascending("expireAt"),
                new IndexOptions().expireAfter(5L, TimeUnit.MINUTES));
    }
}
