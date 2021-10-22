package t4j.ws.domain;

public class Rolename {

    private int idRolename;
    private String designacao;

    public Rolename() {

    }

    public Rolename(Rolename rolename) {
        this.idRolename = rolename.getIdRolename();
        this.designacao = rolename.getDesignacao();
    }

    public Rolename(int idRolename, String designacao){
        setIdRolename(idRolename);
        setDesignacao(designacao);

    }

    public void setIdRolename(int idRolename) {
        this.idRolename = idRolename;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }



    public int getIdRolename() {
        return idRolename;
    }

    public String getDesignacao() {
        return designacao;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rolename)) return false;
        Rolename rolename = (Rolename) o;
        return getDesignacao().equals(rolename.getDesignacao());
    }

    @Override
    public String toString() {
        return designacao;
    }
}
