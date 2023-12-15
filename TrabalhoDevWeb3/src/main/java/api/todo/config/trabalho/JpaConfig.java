package api.todo.config.trabalho;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("api.todo.model.trabalho")
public class JpaConfig {
}
