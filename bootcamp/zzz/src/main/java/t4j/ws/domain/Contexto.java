package t4j.ws.domain;

import t4j.ws.exception.KeyInvalidaException;

import java.util.Random;

public class Contexto {

    private static final String APP_KEY = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";
    private boolean valido;
    private String contexto;
    private int idContexto;

    public Contexto(String appKey) {
        if(appKey.equals(APP_KEY)) {
            setContexto(generateContext());
            setValido(true);
        }
        else {
            throw new KeyInvalidaException("Chave de acesso inválida");
        }
    }

    public Contexto(String appContext, boolean valido) {
        this.contexto = appContext;
        this.valido = valido;
    }

    public Contexto() {

    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public void setIdContexto(int idContexto) {
        this.idContexto = idContexto;
    }

    public String getContexto() {
        return contexto;
    }

    public int getIdContexto() {
        return idContexto;
    }

    public boolean isValido() {
        return valido;
    }

    public String generateContext() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String toString() {
        return contexto;
    }
}
