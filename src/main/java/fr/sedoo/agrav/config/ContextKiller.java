package fr.sedoo.agrav.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ContextKiller {

		@Autowired
	    private ApplicationContext context;
	 
	    public void shutdown(String message) {
	    	log.error("Shutdown of application: "+message);
	    	 ((ConfigurableApplicationContext) context).close();
	    }
	    
	    
	
}
