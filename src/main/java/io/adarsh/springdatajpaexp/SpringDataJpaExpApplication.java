package io.adarsh.springdatajpaexp;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.*;

@SpringBootApplication
@EnableTransactionManagement
@EnableElasticsearchRepositories("io.adarsh.springdatajpaexp.reposES")
public class SpringDataJpaExpApplication {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager; // This entity manager is shared across the whole application
	// so we can't write in an transaction because in between some other operation
	// can be performed over the shared entity manager although we can use type = PersistenceContext.Extended
	// which allows us to persist the data to the PersistentContext without the transaction but
	// for flushing it to the PersistentStorage we have to wrap up it into an transaction.

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaExpApplication.class, args);
		System.out.println("Executed");
		Thread thread = new Thread(() -> {
			SpringDataJpaExpApplication.main(new String[]{});
		});
//		thread.start();
	}

//	@Bean
//	public Module datatypeHibernateModule() {
//		return new Hibernate5Module();
//	}

}
