package t4j.ws.dto;

public class RolenameDTO {

    private int idRolename;
    private String designacao;


    public RolenameDTO(){

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
    public String toString() {
        return designacao;
    }
}
