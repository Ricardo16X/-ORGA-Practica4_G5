package orga.practica_04;

import java.awt.Color;

import javax.swing.JLabel;

public class Envio_Datos implements Runnable {

    JLabel[][] tablero;
    String coordenadas = "";

    public Envio_Datos(int X, JLabel[][] tablero) {
        // TODO Auto-generated constructor stub
        this.tablero = tablero;
    }
    //

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (true) {
                for (int i = 0; i < tablero.length; i++) {
                    coordenadas = "";
                    switch(i){
                        case 0:
                            coordenadas += "0000";
                            break;
                        case 1:
                            coordenadas += "1000";
                            break;
                        case 2:
                            coordenadas += "0100";
                            break;
                        case 3:
                            coordenadas += "1100";
                            break;
                        case 4:
                            coordenadas += "0010";
                            break;
                        case 5:
                            coordenadas += "1010";
                            break;
                        case 6:
                            coordenadas += "0110";
                            break;
                        case 7:
                            coordenadas += "1110";
                            break;
                        case 8:
                            coordenadas += "0001";
                            break;
                        case 9:
                            coordenadas += "1001";
                            break;
                        case 10:
                            coordenadas += "0101";
                            break;
                        case 11:
                            coordenadas += "1101";
                            break;
                    }
                    for (int j = 11; j >= 0; j--) {
                        if (tablero[i][j].getBackground() != Color.WHITE) {
                            // Enviar 1
                            coordenadas = coordenadas + "1";
                        } else {
                            // Enviar 0
                            coordenadas = coordenadas + "0";
                        }
                    }
                    System.out.println("La cadena que envie: " + coordenadas);
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
