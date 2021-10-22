package t4j.ws.domain;

public class Sessao {

    private int idAppContext;
    private int idSessao;
    private int idRolename;
    private String emailUtilizador;


    public Sessao(int idAppContext, int idSessao, int rolename, String emailUtilizador) {
        setContexto(idAppContext);
        setIdSessao(idSessao);
        setRolename(rolename);
        setEmailUtilizador(emailUtilizador);

    }

    public Sessao(int idRolename, int idAppContext, String emailUtilizador) {
        setRolename(idRolename);
        setContexto(idAppContext);
        setEmailUtilizador(emailUtilizador);
    }

    public Sessao() {

    }

    public void setContexto(int idAppContext) {
        this.idAppContext = idAppContext;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public void setRolename(int idRolename) {
        this.idRolename = idRolename;
    }

    public void setEmailUtilizador(String emailUtilizador) {
        this.emailUtilizador = emailUtilizador;
    }

    public int getContexto() {
        return idAppContext;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public int getRolename() {
        return idRolename;
    }

    public String getEmailUtilizador() {
        return emailUtilizador;
    }
}
