package DBsave;
import DBconnection.DBconnection;
import java.sql.*;
/**
 *
 * @author reych
 */
public class DBsave {

    public DBsave() {
        
    }
    
       public static void addUser(String types, String values) {
        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement("insert into users ("
                    +types+") values (" + values + ");");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            
        }
    }

    
}
