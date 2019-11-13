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
        Jugador[] lista = new Jugador[this.listaJugadores.size()];
        int contador = 0;
        for(Jugador j:this.listaJugadores){
            lista[contador] = j;
            contador ++;
        }
        Jugador[] ordenado = ordenarTopPuntos(lista);
        String[] a = new String [ordenado.length];
        for(int n =0; n<ordenado.length ;n++){
            a[n] = "player:"+ordenado[n].getUsername() +" score:"+ordenado[n].getPuntos();
        }
        return a;
    }
    
    private Jugador[] ordenarTopPuntos(Jugador[] arreglo){
      Jugador auxiliar;
      Jugador[] arregloOrdenado;
      for(int i = 1; i < arreglo.length; i++)
      {
        for(int j = 0;j < arreglo.length-i;j++)
        {
          if(arreglo[j].getPuntos() > arreglo[j+1].getPuntos())
          {
            auxiliar = arreglo[j];
            arreglo[j] = arreglo[j+1];
            arreglo[j+1] = auxiliar;
          }   
        }
      }
      arregloOrdenado = arreglo;
      return arregloOrdenado;
    }
    
    public String[] getTopTiempos(){
        Jugador[] lista = new Jugador[this.listaJugadores.size()];
        int contador = 0;
        for(Jugador j:this.listaJugadores){
            lista[contador] = j;
            contador ++;
        }
        Jugador[] ordenado = ordenarTopPuntos(lista);
        String[] a = new String [ordenado.length];
        for(int n =0; n<ordenado.length ;n++){
            a[n] = "player:"+ordenado[n].getUsername() +" timpo:"+ordenado[n].getTiempo();
        }
        return a;
    }
    
    public Jugador[] ordenarTopTiempos(Jugador[] arreglo){
      Jugador auxiliar;
      Jugador[] arregloOrdenado;
      for(int i = 0; i < arreglo.length-1; i++){
        for(int j = 0;j < arreglo.length-i-1;j++){
          if((int)arreglo[j+1].getTiempo() < (int)arreglo[j].getTiempo())
          {
            auxiliar = arreglo[j+1];
            arreglo[j+1] = arreglo[j];
            arreglo[j] = auxiliar;
          }   
        }
      }
      arregloOrdenado = arreglo;
      return arregloOrdenado;
    }
}
