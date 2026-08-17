import java.sql.*;

public class UserAuthJDBC {

    public static boolean login(String username, String password) {

        String query = "SELECT * FROM user WHERE username=? AND password=?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}