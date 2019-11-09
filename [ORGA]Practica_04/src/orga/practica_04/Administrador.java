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
    /*Esta lista tendra a todos los jugadores en la aplicacion junto con sus datos
    con esta lista se podran aceptar las solicitudes de registro*/
    private LinkedList<Jugador> listaJugadores;
    
    public Administrador (String username,String password){
        super(username,password);
        this.listaJugadores = new LinkedList<>();
    }

    public LinkedList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(LinkedList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
    
    public String[] getTopPuntos(){
        String []a;
        a = new String[this.listaJugadores.size()];
        
        return a;
    }
    
    public String[] getTopTiempos(){
        String[] a;
        a = new String[this.listaJugadores.size()];
        
        return a;
    }
}
