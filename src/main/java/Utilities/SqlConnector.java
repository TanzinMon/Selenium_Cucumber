package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {
    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);

    private static final String User = ReadConfigFiles.getPropertyValues("DbUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DbPassword");
    private static final String Host = ReadConfigFiles.getPropertyValues("DBHost");
    private static final String Port = ReadConfigFiles.getPropertyValues("DBPort");
    private static final String DBName = ReadConfigFiles.getPropertyValues("DbName");

    //jdbc:postgresql://<database_host>:<port>/<database_name>
    //"jdbc:postgresql://localhost:5432/mortgage_calculator";
    private static final String ConnectionUrl = String.format("jdbc:postgresql://%s:%s/%s", Host, Port, DBName);

    private static Connection connect() throws Exception {
        LOGGER.info(ConnectionUrl);
        Connection conn = null;
        try{
        conn = DriverManager.getConnection(ConnectionUrl, User, Password);
            if (conn != null) {
                LOGGER.debug("Database Connection is successful");
            }
        }catch (SQLException e) {
          throw new Exception("SQL Connection Exception is: " + e.getMessage());

        }
         return conn;

    }
        public static ResultSet readData(String SQL) throws Exception {
        ResultSet rs = null;
        try (Connection conn = connect()){
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
        }catch(SQLException e){
            throw new Exception("SQL Result Set Exception: " + e.getMessage());
        }
            return rs;
    }
}
