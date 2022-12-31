package university.app.Interfaces;

import university.app.dao.artistDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

public interface artist {

   Object findAllartist() throws SQLException;

    Collection<artistDAO> findAllartistbycountry(String country) throws SQLException;

    Collection<artistDAO> findAllartistbydate(Calendar date) throws SQLException;

    Collection<artistDAO> findById(long id) throws SQLException;
}
