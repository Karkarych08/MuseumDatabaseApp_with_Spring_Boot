package university.app.Services;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class JDBConnect {

    @Getter
    Connection connection;

    public JDBConnect(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }
}