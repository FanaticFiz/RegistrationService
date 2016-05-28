package com.simpleTeam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;

/**
 * Data source for HSQL db.
 *
 * @author Fiz
 * @version 0.1
 */
@Configuration
public class DataSourceConfig {

    /**
     * DataSource for HSQL.
     *
     * @return DataSource.
     */
    @Bean
    public DataSource dataSource() {
        //jdbc:hsqldb:mem:testdb
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/create-db.sql")
                .build();
        return db;
    }
}
