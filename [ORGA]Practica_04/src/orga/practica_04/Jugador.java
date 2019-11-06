/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orga.practica_04;

/**
 *
 * @author aiyel
 */
public class Jugador extends Usuario {
    
    private float tiempo;
    private int puntos;
    private int nivel;
    
    public Jugador(String username,String password){
        super(username,password);
        this.tiempo = 0;
        this.puntos = 0;
        this.nivel = 1;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
}
