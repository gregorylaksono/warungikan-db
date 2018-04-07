package org.warungikan.db.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.warungikan.db.repository.UserRepository;

@Configuration
@PropertySource("application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.warungikan.db.repository","org.warungikan.db.model"})
@ComponentScan(basePackages = { "org.warungikan.db.*" })
@EntityScan("org.warungikan.db.model")  
public  class SpringConfig {

}
