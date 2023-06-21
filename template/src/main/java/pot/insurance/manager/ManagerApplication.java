package pot.insurance.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ManagerApplication {

	public static void main(String[] args) {
		ApplicationContext a = SpringApplication.run(ManagerApplication.class, args);
		UserRepository b = a.getBean(UserRepository.class);

		b.save(
			User.builder()
				.username("antanas")
				.password("testas")
				.privilege(UserPrivilege.ADMIN)
				.build()
		);
	}

}
