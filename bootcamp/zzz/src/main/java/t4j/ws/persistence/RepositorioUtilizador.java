package t4j.ws.persistence;

import t4j.ws.domain.Password;
import t4j.ws.domain.Utilizador;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.*;
import java.util.List;

public class RepositorioUtilizador {

    private static RepositorioUtilizador repositorioUtilizador;

    private RepositorioUtilizador(){    }

    public static RepositorioUtilizador getInstance(){
        if(repositorioUtilizador == null) {
            repositorioUtilizador = new RepositorioUtilizador();
        }
        return repositorioUtilizador;
    }

    public boolean saveWithoutRole(Utilizador utilizador) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL createUtilizadorWithoutRole(?, ?, ?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, utilizador.getEmail().toString());
            callableStatement.setString(2, utilizador.getUsername());
            callableStatement.setString(3, utilizador.getPassword().toString());

            callableStatement.executeQuery();

            connection.commit();
            return true;

        }

        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    public boolean saveWithRole(Utilizador utilizador) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL createUtilizadorWithRole(?, ?, ?, ?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, utilizador.getEmail().toString());
            callableStatement.setString(2, utilizador.getUsername());
            callableStatement.setString(3, utilizador.getPassword().toString());
            callableStatement.setInt(4, utilizador.getIdRolename());

            callableStatement.executeQuery();

            connection.commit();
            return true;

        }

        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    public Utilizador findByEmail(String email) throws SQLException {
        Utilizador utilizador = new Utilizador();


        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Utilizador " +
                            "WHERE email LIKE ?"
            );

            preparedStatement.setString(1, email);

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                utilizador.setEmail(resultSet.getString(1));
                utilizador.setUsername(resultSet.getString(2));
                utilizador.setPassword(new Password(resultSet.getString(3)));
                utilizador.setIdRolename(resultSet.getInt(4));
            }
        }

        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return  utilizador;
    }

    public List<Utilizador> getAll() {
        return null;
    }

    public Utilizador getByEmail(String email) throws SQLException {
       Utilizador utilizador = new Utilizador();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM Utilizador " +
                            "WHERE email LIKE ?"
            );

            callableStatement.setString(1, email);

            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                utilizador.setEmail(email);
                utilizador.setUsername(resultSet.getString(2));
                utilizador.setPassword(new Password(resultSet.getString(3)));
                utilizador.setIdRolename(resultSet.getInt(4));
            }
        }
        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
       return utilizador;
    }

    public boolean addRoleToUser(Utilizador utilizador, int idRolename) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "UPDATE Utilizador " +
                            "SET idRolename = ? " +
                            "WHERE email LIKE ?"
            );

            callableStatement.setInt(1, idRolename);
            callableStatement.setString(2, utilizador.getEmail().toString());

            callableStatement.executeQuery();

            return true;
        }
        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    public boolean deleteRoleFromUser(Utilizador utilizador) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Utilizador " +
                            "SET idRolename = null WHERE email LIKE ?"
            );

            preparedStatement.setString(1, utilizador.getEmail().toString());
            preparedStatement.executeQuery();
            return true;
        }
        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }


}
