/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t4j.ws.domain;


import t4j.ws.exception.NomeInvalidoException;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author CAD
 */

public class Utilizador implements Serializable{
   

    private String username;
    private Email email;
    private Password password;
    private int idRolename;
    private String rolename;

    public Utilizador(){
    }

    public Utilizador(Email emailUt, String username, Password password){
        this.email = emailUt;
        setUsername(username);
        setPassword(password);
    }

    public Utilizador(Utilizador utilizador) {
        this.email = utilizador.getEmail();
        this.username = utilizador.getUsername();
        this.password = utilizador.getPassword();
        this.idRolename = utilizador.getIdRolename();
    }

    public Utilizador(String emailUtilizador, String username, String password, int idRolename) {
        setEmail(new Email(emailUtilizador));
        setUsername(username);
        setPassword(new Password(password));
        setIdRolename(idRolename);

    }

    public Utilizador(String email, String username) {
        setUsername(username);
        setEmail(new Email(email));
    }

    public void setUsername(String username){
        if (username == null || username.trim().isEmpty()) {
            throw new NomeInvalidoException("username é inválido!");
        }
        this.username = username;
    }

    public void setEmail(Email email){
        this.email = new Email(email);
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public void setPassword(Password password){
        this.password = password;
    }

    public void setIdRolename(int idRolename){
        this.idRolename = idRolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUsername(){
        return username;
    }

    public Email getEmail(){
        return email;
    }  

    public Password getPassword(){
        return password;
    } 

    public int getIdRolename(){
        return idRolename;
    }

    public String getRolename() {
        return rolename;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizador)) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(password, that.password);
    }

}
