package com.crypto.core.users.repository.configs;

import com.crypto.core.users.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;

import javax.annotation.PostConstruct;

@Configuration
public class UserIndexConfig {
    private final MongoOperations mongoOperations;

    public UserIndexConfig(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @PostConstruct
    public void init() {
        mongoOperations.indexOps(UserEntity.class)
                .ensureIndex(
                        new Index(UserEntity.Field.EMAIL, Sort.Direction.ASC).sparse().unique()
                );
    }
}