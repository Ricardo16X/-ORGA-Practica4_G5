package orga.practica_04;

import java.awt.Color;

import javax.swing.JLabel;

public class Envio_Datos implements Runnable{
	JLabel[][] tablero;
	public Envio_Datos(int X, JLabel[][] tablero) {
		// TODO Auto-generated constructor stub
		this.tablero = tablero;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				for (int i = 0; i < tablero.length; i++) {
					//System.out.println("Enviando Columna " + (i + 1));
					for (int j = 11; j >= 0; j--) {
						//System.out.println("Enviando Fila " + (j + 1));
						if (tablero[i][j].getBackground() != Color.RED) {
							//System.out.println(1);
						}else {
							//System.out.println(0);
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
