package ec.com.sofka.config;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "ec.com.sofka.serviceAdapter",
        reactiveMongoTemplateRef = "accountReactiveMongoTemplate")
public class AccountMongoConfig {
    @Value("${spring.data.mongodb.accounts-uri}")
    private String accountsUri;

    @Primary
    @Bean(name = "accountsReactiveDatabaseFactory")
    public ReactiveMongoDatabaseFactory accountsDatabaseFactory() {
        return new SimpleReactiveMongoDatabaseFactory(new ConnectionString(accountsUri));
    }

    @Primary
    @Bean(name = "accountReactiveMongoTemplate")
    public ReactiveMongoTemplate accountsMongoTemplate(@Qualifier("accountsReactiveDatabaseFactory") ReactiveMongoDatabaseFactory accountsDatabaseFactory) {
        return new ReactiveMongoTemplate(accountsDatabaseFactory);
    }
}