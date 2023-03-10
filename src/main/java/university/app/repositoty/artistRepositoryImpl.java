package university.app.repositoty;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import university.app.Interfaces.artistRepository;
import university.app.Services.JDBConnect;
import university.app.dao.artistDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


@Repository
@RequiredArgsConstructor
public class artistRepositoryImpl implements artistRepository {


    private final JDBConnect jdbConnect;


    @Override
    public Collection<artistDAO> findOlderThenDate(Calendar date) throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement prepareStatement = jdbConnect.getConnection().prepareStatement("SELECT * FROM artist where dateofbirth>?")) {
            prepareStatement.setDate(1, new Date(date.getTime().getTime()));
            resultSet = prepareStatement.executeQuery();
        }
        return getArtistDAOS(resultSet);
    }

    @Override
    public Collection<artistDAO> findAll() throws SQLException {
        try (ResultSet resultSet = jdbConnect.getConnection().createStatement().executeQuery("SELECT * FROM artist")) {
            return getArtistDAOS(resultSet);
        }
    }

    @Override
    public Collection<artistDAO> findAllByCountry(String country) throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement prepareStatement = jdbConnect.getConnection().prepareStatement("SELECT * FROM artist where country=?")) {
            prepareStatement.setString(1, country);
            resultSet = prepareStatement.executeQuery();
        }
        return getArtistDAOS(resultSet);
    }

    private Collection<artistDAO> getArtistDAOS(ResultSet resultSet) throws SQLException {
        ArrayList<artistDAO> artists = new ArrayList<>();
        while (resultSet.next()){
            artistDAO artist = new artistDAO(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("secondname"),
                    resultSet.getString("familyname"),
                    resultSet.getDate("dateofbirth"),
                    resultSet.getString("country"),
                    resultSet.getDate("dateofdeath"));
            artists.add(artist);
        }
        return artists;
    }

    @Override
    public Collection<artistDAO> findById(long id) throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement prepareStatement = jdbConnect.getConnection().prepareStatement("SELECT * FROM artist where id=?")) {
            prepareStatement.setLong(1, id);
            resultSet = prepareStatement.executeQuery();
        }
        return getArtistDAOS(resultSet);
    }
}
