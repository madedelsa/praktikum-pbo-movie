package koneksi;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Made Delsa
 */
public class Connector {
    static  Connection con;
    
    public static Connection connection(){
        if(con==null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("praktikum_pbo_movie_db");
            data.setUser("root");
            data.setPassword("");
            try {
                con = data.getConnection();
                System.out.println("Koneksi berhasil");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Koneksi gagal");
            }
            
        }
        return con;
    }
}
