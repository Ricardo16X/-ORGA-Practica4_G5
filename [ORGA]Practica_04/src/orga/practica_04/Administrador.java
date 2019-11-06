/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orga.practica_04;

import java.util.LinkedList;

/**
 *
 * @author aiyel
 */
public class Administrador extends Usuario {
    
    private LinkedList<Jugador> listaJugadores;
    
    public Administrador (String username,String password){
        super(username,password);
        this.listaJugadores = null;
    }

    public LinkedList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(LinkedList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
    
    
    
}
