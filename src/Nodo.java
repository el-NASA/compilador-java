/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoralogica;

/**
 *
 * @author Alba
 */
public class Nodo {

    String token;
    String descripcion;
    
    public Nodo() {
        
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public String getToken() {
        return token;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
}
