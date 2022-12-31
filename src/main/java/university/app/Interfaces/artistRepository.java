package university.app.Interfaces;

import university.app.dao.artistDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

public interface artistRepository {
    Collection<artistDAO> findOlderThenDate(Calendar date) throws SQLException;
    Collection<artistDAO> findAll() throws SQLException;
    Collection<artistDAO> findAllByCountry(String country) throws SQLException;
    Collection<artistDAO> findById(long id) throws SQLException;
}
