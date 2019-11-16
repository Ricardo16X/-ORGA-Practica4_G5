/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orga.practica_04;

import pruebas.prueba1;

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
        int i = (int) (Math.random() * 5) + 1;
        for (int j = 0; j < 10; j++) {
            System.out.println("numero: " + i);
            i = (int) (Math.random() * 5) + 1;
        }
        
    }
    
}
