package com.petercoulton.spring.putbehaviour;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class PutBehaviourApplication {
	private static final Logger log = LoggerFactory.getLogger(PutBehaviourApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PutBehaviourApplication.class, args);
	}
}
