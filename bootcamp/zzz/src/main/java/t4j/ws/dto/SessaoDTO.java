package t4j.ws.dto;

public class SessaoDTO {

    private int idAppContextDTO;
    private int idSessaoDTO;
    private int idRolenameDTO;
    private String emailUtilizadorDTO;

    public SessaoDTO() {

    }


    public void setIdAppContextDTO(int idAppContextDTO) {
        this.idAppContextDTO = idAppContextDTO;
    }

    public void setIdSessaoDTO(int idSessaoDTO) {
        this.idSessaoDTO = idSessaoDTO;
    }

    public void setRolenameDTO(int idRolenameDTO) {
        this.idRolenameDTO = idRolenameDTO;
    }

    public void setEmailUtilizadorDTO(String emailUtilizadorDTO) {
        this.emailUtilizadorDTO = emailUtilizadorDTO;
    }

    public int getIdAppContextDTO() {
        return idAppContextDTO;
    }

    public int getIdSessaoDTO() {
        return idSessaoDTO;
    }

    public int getRolenameDTO() {
        return idRolenameDTO;
    }

    public String getEmailUtilizadorDTO() {
        return emailUtilizadorDTO;
    }
}
