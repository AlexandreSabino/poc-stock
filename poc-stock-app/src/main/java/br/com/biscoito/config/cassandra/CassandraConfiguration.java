package br.com.biscoito.config.cassandra;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "stock";
    }
}