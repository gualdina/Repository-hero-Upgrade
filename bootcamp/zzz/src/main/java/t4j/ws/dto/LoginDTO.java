package t4j.ws.dto;

import t4j.ws.domain.Email;
import t4j.ws.domain.Password;

public class LoginDTO {

    private Email email;
    private Password password;

    public LoginDTO() {

    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Email:" + email;
    }
}
