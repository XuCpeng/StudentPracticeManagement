package cn.medemede.j2ee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Saber
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan
public class J2eeApplication {

	public static void main(String[] args) {
		SpringApplication.run(J2eeApplication.class, args);
	}
}
