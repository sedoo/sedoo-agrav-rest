package fr.sedoo.agrav.config;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ApplicationConfig {
	
	@Autowired
	ContextKiller contextKiller;
	
	@Value("${dataFolder}")
	private String dataFolderName;
	
	private String sqlSchemaFileName ="schema.sql";
	
	private String sqlDataFileName ="data.sql";
	
	public File getSqlSchemaFile() {
		File dataFolder = new File(dataFolderName);
		dataFolder.mkdirs();
		File result =  new File(dataFolder, sqlSchemaFileName);
		return result;
	}
	
	public File getSqlDataFile() {
		File dataFolder = new File(dataFolderName);
		dataFolder.mkdirs();
		File result =  new File(dataFolder, sqlDataFileName);
		return result;
	}
	
	@PostConstruct
	public void configCheck() {
		if (!getSqlSchemaFile().exists()) {
				contextKiller.shutdown("Sql schema file is missing");
		}
		
		if (!getSqlDataFile().exists()) {
			contextKiller.shutdown("Sql data file is missing");
		}
	}
	
	
}
