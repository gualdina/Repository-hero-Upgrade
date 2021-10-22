package t4j.ws.utils;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class DBConnectionHandler {

    private static final String JDBC_URL = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String USERNAME = "UPSKILL_BD_TURMA1_01";
    private static final String PASSWORD = "qwerty";

    private static DBConnectionHandler instance;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private CallableStatement callableStatement;

    public static DBConnectionHandler getInstance() {
        if (instance == null) {
            instance = new DBConnectionHandler();
        }
        return instance;
    }

    private DBConnectionHandler() {

        connection = null;
        preparedStatement = null;
        resultSet = null;
        statement = null;
        callableStatement = null;

    }

    public Connection openConnection() throws SQLException {
        try {

            OracleDataSource ds = new OracleDataSource();
            ds.setURL(JDBC_URL);
            connection = ds.getConnection(USERNAME, PASSWORD);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getMessage();
        }
        return connection;
    }

    public String closeAll() {

        StringBuilder message = new StringBuilder("");

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            resultSet = null;
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            preparedStatement = null;
        }

        if(callableStatement != null) {
            try {
                callableStatement.close();
            }
            catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            statement = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            connection = null;
        }
        return message.toString();
    }
}
