package cn.medemede.spm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Saber
 */
@SpringBootApplication
@ServletComponentScan
public class SpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpmApplication.class, args);
    }
}
