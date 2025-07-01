package com.example.soa.projectZavala.link;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SwaggerLinkPrinter implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		 System.out.println("\n==== Swagger UI available at: http://localhost:8080/swagger-ui/index.html ====\n");
		
	}


	 
}
