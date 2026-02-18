import java.sql.Connection;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {
  private static Dotenv dotenv = Dotenv.load();
  private static final String DB_URL = dotenv.get("DB_URL");
  private static final String DB_USER = dotenv.get("DB_USER");
  private static final String DB_PASS = dotenv.get("DB_PASS");

  // getConnection into db using dotenv
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
  }

  // close connection using existing connection
  public static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
