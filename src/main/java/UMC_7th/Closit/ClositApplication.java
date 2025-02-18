package UMC_7th.Closit;

import jakarta.annotation.PostConstruct;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class ClositApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		System.out.println(new Date());
		System.out.println(new DateTime());
		System.out.println(Date.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()));
		SpringApplication.run(ClositApplication.class, args);
	}

}
