package UMC_7th.Closit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClositApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClositApplication.class, args);
	}

}
