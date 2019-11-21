package orga.practica_04;

import java.awt.Color;

import javax.swing.JLabel;

public class Envio_Datos implements Runnable {

    JLabel[][] tablero;

    public Envio_Datos(int X, JLabel[][] tablero) {
        // TODO Auto-generated constructor stub
        this.tablero = tablero;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (true) {
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 11; j >= 0; j--) {
                        if (j == 11) {
                            if (tablero[i][j].getBackground() != Color.WHITE) {
                                /*
                                Enviar 0
                                dormir
                                Enviar 0
                                dormir
                                Enviar 0
                                dormir
                                Enviar 1
                                */
                            } else {
                                /*
                                Enviar 0
                                dormir
                                Enviar 0
                                dormir
                                Enviar 0
                                dormir
                                Enviar 0
                                */
                            }
                        } else {
                            if (tablero[i][j].getBackground() != Color.WHITE) {
                                // Enviar 1
                            } else {
                                // Enviar 0
                            }
                        }
                    }
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
