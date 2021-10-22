package t4j.ws.dto;

public class ErroDTO {

    private String error;

    public ErroDTO(Exception exception) {
        error = exception.getMessage();
        exception.printStackTrace();
    }

    public ErroDTO() {}

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
