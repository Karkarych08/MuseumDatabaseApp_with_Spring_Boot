package university.app.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.app.Interfaces.artist;
import university.app.dao.artistDAO;
import university.app.repositoty.artistRepositoryImpl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class artistService implements artist {

    private final artistRepositoryImpl aRep;

    @Override
    public Collection<artistDAO> findAllartist() throws SQLException {

        return aRep.findAll();
    }

    public Collection<artistDAO> findAllartistbycountry(String country) throws SQLException {

        return aRep.findAllByCountry(country);
    }

    public Collection<artistDAO> findAllartistbydate(Calendar date) throws SQLException {

        return aRep.findOlderThenDate(date);
    }

    public Collection<artistDAO> findById(long id) throws SQLException{
        return aRep.findById(id);
    }

}
