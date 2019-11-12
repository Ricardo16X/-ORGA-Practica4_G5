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
public class ORGAPractica_04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Instancia de la ventana principal del juego:
        LogIn loginInicio = new LogIn();
        loginInicio.setVisible(true);
        
        Administrador admin = new Administrador("admin","123");
        Jugador p1 = new Jugador("p1","1");
        Jugador p2 = new Jugador("p2","1");
        Jugador p3 = new Jugador("p3","1");
        Jugador p4 = new Jugador("p4","1");
        p1.setPuntos(4);
        p2.setPuntos(2);
        p3.setPuntos(3);
        p4.setPuntos(1);
        p1.setTiempo(1);
        p2.setTiempo(3);
        p3.setTiempo(5);
        p4.setTiempo(7);
        admin.getListaJugadores().add(p1);
        admin.getListaJugadores().add(p2);
        admin.getListaJugadores().add(p3);
        admin.getListaJugadores().add(p4);
        String a = "";
        for(String j:admin.getTopPuntos()){
            a += j +" -> ";
        }
        System.out.println(a);
        
        String b = "";
        for(String j:admin.getTopTiempos()){
            b += j +" -> ";
        }
        System.out.println(b);
    }
    
}
