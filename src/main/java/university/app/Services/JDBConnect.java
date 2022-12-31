package university.app.Services;

import lombok.Getter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import university.app.config.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JDBConnect {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Settings.class);

    Settings setting = ctx.getBean(Settings.class);

    @Getter
    private final Connection connection = DriverManager.getConnection(setting.getURL(),setting.getUsername(),setting.getPassword());

    public JDBConnect() throws SQLException {
    }


}