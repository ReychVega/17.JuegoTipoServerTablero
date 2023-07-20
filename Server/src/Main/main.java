package Main;

import Server.Server;
import java.io.IOException;
import java.sql.*;
/**
 *
 * @author reych
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Server server = new Server(); 
        server.start();
  
    }

}
