package dao_implements;
import java.util.List;
import model.*;

/**
 * @author Made Delsa
 */
public interface DataMovieImplements {
    public void insert(DataMovie m);
    public void update(DataMovie m);
    public void delete(String judul);
    public List<DataMovie> getAll();
}
