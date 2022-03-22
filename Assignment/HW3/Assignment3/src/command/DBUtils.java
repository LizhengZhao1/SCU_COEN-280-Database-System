package command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    /**
     * Create A New Database Connection
     * @return Connection Object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String oracleURL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userName = "system";
        String password = "oracle";
        Connection connection = DriverManager.getConnection(oracleURL, userName, password);
        return connection;
    }

    /**
     * close connection, release resources
     * @param connection
     */
    public static void closeConnection(Connection connection){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
