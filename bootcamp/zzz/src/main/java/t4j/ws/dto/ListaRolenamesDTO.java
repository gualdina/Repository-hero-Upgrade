package t4j.ws.dto;

import java.util.List;

public class ListaRolenamesDTO {

    private List<RolenameDTO> rolenamesDTO;

    public ListaRolenamesDTO() {

    }

    public void setRolenamesDTO(List<RolenameDTO> rolenamesDTO) {
        this.rolenamesDTO = rolenamesDTO;
    }

    public List<RolenameDTO> getRolenamesDTO() {
        return rolenamesDTO;
    }

}
