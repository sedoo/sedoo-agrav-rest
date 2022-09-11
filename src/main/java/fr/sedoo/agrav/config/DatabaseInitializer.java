package fr.sedoo.agrav.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseInitializer {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ApplicationConfig config;
	
	@Autowired
	ContextKiller contextKiller;
	
	
	@PostConstruct
	public void init() {
		
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(new FileSystemResource(config.getSqlSchemaFile()));
		try {
	         databasePopulator.execute(dataSource);
	         log.info("Schéma script executed");
	     } catch (ScriptException e) {
	    	 String message = "An error has occured while executing schema script: "+ExceptionUtils.getFullStackTrace(e);
	    	 contextKiller.shutdown(message);
	     }
		
		databasePopulator = new ResourceDatabasePopulator(new FileSystemResource(config.getSqlDataFile()));
		try {
	         databasePopulator.execute(dataSource);
	     } catch (ScriptException e) {
	    	 String message = "An error has occured while executing data script: "+ExceptionUtils.getFullStackTrace(e);
	    	 contextKiller.shutdown(message);
	     }
	}

}
