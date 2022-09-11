package fr.sedoo.agrav.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class BeanConfig {
	
	@Bean
	public DataSource getDataSource()
	{
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
		         .build();
		return db;
	}

	
	 @Bean
	    public JdbcTemplate createJdbcTeamplate() {

		 JdbcTemplate template = new JdbcTemplate();
	        template.setDataSource(getDataSource());
	        return template;
	    }
}
