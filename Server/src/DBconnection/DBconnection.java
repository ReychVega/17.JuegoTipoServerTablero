package DBconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reych
 *
 */
public class DBconnection {

    //attributes
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dbUser = "k6hf5dje06shwqcnxzzb";
    private static final String dbPassword = "pscale_pw_nFx5NOl0csS62d1OIBy7ILSDzxf5QEx979r6aIb9JWo";
    private static final String dbServer = "jdbc:mysql://aws.connect.psdb.cloud/users";
    private static final String sslOptions = "?sslMode=VERIFY_IDENTITY&sslCa=/etc/ssl/certs/ca-certificates.crt";
   
           
    /*
    *getConnection() is method to create a connection between jsp and a dataBase
    *return=Connection.
    */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(dbServer, dbUser, dbPassword);
            return connection;
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex.toString());
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC driver not found: " + ex.getMessage());
        }
        return null;
    }// end getConnection()

    /*
    *getConnection() is method to close a connection and statement between jsp and a dataBase
    */
    public void closeConnection(Connection conex, Statement sT) {
        try {
            conex.close();
            sT.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end closeConnection(..)
     
}


