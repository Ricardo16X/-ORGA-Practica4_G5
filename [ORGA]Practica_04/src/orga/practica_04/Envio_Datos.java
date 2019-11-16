package juego;

public class Envio_Datos implements Runnable{
	int CoordenadaX, CoordenadaY;
	public Envio_Datos(int X, int Y) {
		// TODO Auto-generated constructor stub
		CoordenadaX = X;
		CoordenadaY = Y;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				System.out.println("Enviando X = " + CoordenadaX);
				System.out.println("Enviando Y = " + CoordenadaY);
				Thread.sleep(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setX(int X) {
		CoordenadaX = X;
	}
	
	public void setY(int Y) {
		CoordenadaY = Y;
	}
}
