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
        System.out.println("Servidor");

        try {
            Server server = new Server(5025);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

       
       /* //testing connection to dataBase mySql web 
        Connection con= DBconnection.DBconnection.getConnection();
        if (con!=null) {
            System.out.println("Successful");
        }
        */
    }

}
