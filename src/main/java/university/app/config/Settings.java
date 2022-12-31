package university.app.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@PropertySource("classpath:app.properties")
public class Settings {
    @Value("${something.url}")
    private String URL;

    @Value("${something.username}")
    private String username;

    @Value("${something.password}")
    private String password;
}
