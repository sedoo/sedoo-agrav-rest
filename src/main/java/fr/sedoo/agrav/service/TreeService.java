package fr.sedoo.agrav.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping(value = "tree/v1_0")
public class TreeService {

	@Autowired
	JdbcTemplate template;

	@RequestMapping(value = "/stations", method = RequestMethod.GET)
	public String list() {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("classpath:stations.json");
		 try (Reader reader = new InputStreamReader(resource.getInputStream(), Charset.defaultCharset())) {
	            return FileCopyUtils.copyToString(reader);
	        } catch (IOException e) {
	        	return "";
	        }
		
	}
}
