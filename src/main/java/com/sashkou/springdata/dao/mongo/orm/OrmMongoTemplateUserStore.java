package com.sashkou.springdata.dao.mongo.orm;

import com.sashkou.springdata.config.MongoConfig;
import com.sashkou.springdata.service.User;
import com.sashkou.springdata.service.UserStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sashkou.springdata.dao.mongo.orm.Constants.COLLECTION_NAME;

@Slf4j
@Repository(value = "ormMongoTemplateUserStore")
public class OrmMongoTemplateUserStore extends AbstractMongoTemplateWrapper implements UserStore {

    private final MongoConfig mongoConfig;
    private final JsonConverter<User> jsonConverter;
    private final MongoCommandGenerator commandGenerator;

    public OrmMongoTemplateUserStore(MongoConfig mongoConfig, MongoCommandGenerator commandGenerator) {
        this.mongoConfig = mongoConfig;
        this.jsonConverter = new JsonConverter<>(User.class);
        this.commandGenerator = commandGenerator;
    }

    @Override
    protected MongoConfig getMongoConfig() {
        return mongoConfig;
    }

    @Override
    public List<User> findAll() {
        String command = commandGenerator.generateFindAllCommand(COLLECTION_NAME);
        List<String> result = super.execute(command);

        return jsonConverter.fromJson(result);
    }

    @Override
    public List<User> findAll(int order, String... sortProperties) {
        log.error("method not implemented");
        return null;
    }

    @Override
    public User findById(Long id) {
        log.error("method not implemented");
        return null;
    }

    @Override
    public void save(User user) {
        log.error("method not implemented");
    }
}
