package t4j.ws.dto;

public class ContextoDTO {

    private String appContext;

    public ContextoDTO() {

    }

    public void setAppContext(String appContext) {
        this.appContext = appContext;
    }

    public String getAppContext() {
        return appContext;
    }

    @Override
    public String toString() {
        return appContext;
    }
}
