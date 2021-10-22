package t4j.ws.persistence;

import t4j.ws.domain.Contexto;
import t4j.ws.domain.Sessao;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.*;


public class RepositorioSessao {

    private static RepositorioSessao repositorioSessao;

    private RepositorioSessao(){    }
    public static RepositorioSessao getInstance(){
        if(repositorioSessao == null) {
            repositorioSessao = new RepositorioSessao();
        }
        return repositorioSessao;
    }

    public void saveContext(Contexto contexto) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL createContext(?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, contexto.toString());

            callableStatement.executeQuery();

            connection.commit();

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

    }

    public boolean saveSessao(Sessao sessao) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createSessao(?, ?, ?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setInt(1, sessao.getContexto());
            callableStatement.setInt(2, sessao.getRolename());
            callableStatement.setString(3, sessao.getEmailUtilizador());

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

    public Contexto findContextByString(String context) throws SQLException {

        Contexto contexto = new Contexto();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * From AppContext WHERE value LIKE ?"
            );

            preparedStatement.setString(1, context);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
                contexto.setIdContexto(resultSet.getInt(1));
                contexto.setContexto(resultSet.getString(2));
                contexto.setValido(resultSet.getInt(3) != 3);
                /*int idEstadoContext = resultSet.getInt(3);
                if(idEstadoContext == 2) {
                    contexto.setValido(true);
                }
                else if(idEstadoContext == 3) {
                    contexto.setValido(false);
                }*/
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
        return contexto;
    }

    public boolean contextInvalid(Contexto contexto) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            connection.setAutoCommit(false);

            CallableStatement callableStatement = connection.prepareCall(
                    "UPDATE AppContext " +
                            "SET idEstadoContext = 3 " +
                            "WHERE value LIKE ?"
            );

            callableStatement.setString(1, contexto.getContexto());
            callableStatement.executeQuery();

            connection.commit();
            contexto.setValido(false);

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


    public Sessao findByContext(String appContext) throws SQLException {
        Sessao sessao = new Sessao();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareCall(
                    "SELECT * " +
                            "FROM UserSession " +
                            "INNER JOIN AppContext " +
                            "ON UserSession.idAppContext = AppContext.idAppContext " +
                            "WHERE AppContext.value LIKE ?"
            );

            preparedStatement.setString(1, appContext);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
               sessao.setIdSessao(resultSet.getInt(1));
               sessao.setContexto(resultSet.getInt(2));
               sessao.setRolename(resultSet.getInt(3));
               sessao.setEmailUtilizador(resultSet.getString(4));
            }

            connection.commit();

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
        return sessao;
    }

    public void setUsableOrDiscarded(Contexto contexto) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();
        try {
            connection.setAutoCommit(false);

            CallableStatement callableStatement = connection.prepareCall(
                    "{ ? = call setState(?) }"
            );

            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, contexto.getContexto());
            callableStatement.execute();

            int estado = callableStatement.getInt(1);

            connection.commit();

            contexto.setValido(estado != 3);
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

    }
}
