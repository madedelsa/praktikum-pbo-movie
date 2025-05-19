package dao_datamovie;

import java.sql.*;
import java.util.*;
import koneksi.Connector;
import model.DataMovie;
import dao_implements.DataMovieImplements;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataMovieDAO implements DataMovieImplements {
    Connection connection;

    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE movie SET alur=?, penokohan=?, akting=?, nilai=? WHERE judul=?;";
    final String delete = "DELETE FROM movie WHERE judul=?;";
    final String select = "SELECT * FROM movie;";

    public DataMovieDAO() {
        connection = Connector.connection();
    }

    @Override
    public void insert(DataMovie m) {
        try (PreparedStatement ps = connection.prepareStatement(insert)) {
            ps.setString(1, m.getJudul());
            ps.setDouble(2, m.getAlur());
            ps.setDouble(3, m.getPenokohan());
            ps.setDouble(4, m.getAkting());
            ps.setDouble(5, m.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataMovieDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(DataMovie m) {
        try (PreparedStatement ps = connection.prepareStatement(update)) {
            ps.setDouble(1, m.getAlur());
            ps.setDouble(2, m.getPenokohan());
            ps.setDouble(3, m.getAkting());
            ps.setDouble(4, m.getRating());
            ps.setString(5, m.getJudul());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataMovieDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void delete(String judul) {
        try (PreparedStatement ps = connection.prepareStatement(delete)) {
            ps.setString(1, judul);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataMovieDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<DataMovie> getAll() {
        List<DataMovie> dm = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                DataMovie mv = new DataMovie();
                mv.setId(rs.getInt("id"));
                mv.setJudul(rs.getString("judul"));
                mv.setAlur(rs.getDouble("alur"));
                mv.setPenokohan(rs.getDouble("penokohan"));
                mv.setAkting(rs.getDouble("akting"));
                mv.setRating(rs.getDouble("nilai"));
                dm.add(mv);
            }
        } catch (SQLException e) {
            Logger.getLogger(DataMovieDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return dm;
    }
}
