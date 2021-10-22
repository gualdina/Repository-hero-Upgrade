package t4j.ws.persistence;

import t4j.ws.domain.Rolename;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioRolename {

    private static RepositorioRolename repositorioRolename;

    private RepositorioRolename(){    }
    public static RepositorioRolename getInstance(){
        if(repositorioRolename == null) {
            repositorioRolename = new RepositorioRolename();
        }
        return repositorioRolename;
    }

    public boolean save(Rolename rolename) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createRolename(?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, rolename.getDesignacao());

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

    public List<Rolename> getAll() throws SQLException {
        List<Rolename> rolenames = new ArrayList<Rolename>();
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM Rolename"
            );

            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                int idRolename = resultSet.getInt(1);
                String designacao = resultSet.getString(2);
                rolenames.add(new Rolename(idRolename, designacao));
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
        return rolenames;
    }

    public int getByName(String rolename) throws SQLException {
        int idRolename = 0;

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idRolename FROM Rolename WHERE designacao LIKE ?"
            );

            preparedStatement.setString(1, rolename);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
               idRolename = resultSet.getInt(1);
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

        return idRolename;
    }

    public boolean deleteRolename(String rolename) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Rolename WHERE designacao LIKE ?"
            );

            preparedStatement.setString(1, rolename);
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

    public Rolename getById(int idRolename) throws SQLException {
        Rolename rolename = new Rolename();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Rolename WHERE idRolename = ?"
            );

            preparedStatement.setInt(1, idRolename);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){
                rolename.setIdRolename(idRolename);
                rolename.setDesignacao(resultSet.getString(2));
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

        return rolename;
    }

    public Rolename getRolenameByName(String rolename) throws SQLException {
        Rolename rn = new Rolename();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Rolename WHERE designacao LIKE ?"
            );

            preparedStatement.setString(1, rolename);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){
                rn.setIdRolename(resultSet.getInt(1));
                rn.setDesignacao(rolename);
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

        return rn;
    }

    public int getIdByEmail(String email) throws SQLException {
        int idRolename = 0;

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Rolename.idRolename FROM Rolename " +
                            "INNER JOIN Utilizador " +
                            "ON Rolename.idRolename = Utilizador.idRolename " +
                            "WHERE Utilizador.email LIKE ?"
            );

            preparedStatement.setString(1, email);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){
               idRolename = resultSet.getInt(1);
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

        return idRolename;
    }
}
