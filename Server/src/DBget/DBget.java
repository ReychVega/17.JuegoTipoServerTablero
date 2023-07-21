package DBget;

import DBconnection.DBconnection;
import Domain.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class DBget {

    public DBget() {
    }

    public static ArrayList<User> getUsers(String searchCode) {
        ArrayList<User> usersData = new ArrayList<>();
        String query = "";
        try {
            if (!searchCode.isEmpty()) {
                query = "select * from users"
                        + " where user='" + searchCode
                        + "' or password='" + searchCode
                        + "';";
            } else {
                query = "select * from users";
            }

            Connection con = DBconnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                User user = new User();
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                usersData.add(user);
            }

            rs.close();
            stmt.close();
            con.close();

            return usersData;
        } catch (SQLException e) {
            return usersData;
        }

    }

}
