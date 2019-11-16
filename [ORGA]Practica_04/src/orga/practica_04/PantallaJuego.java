package juego;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import juego.PantallaJuego.Coordenadas;

public class PantallaJuego extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Variables de Inicialización
	JLabel[][] tablero;
	ArrayList<Coordenadas> cuerpo;
	JPanel panel;
	// Variables de Movimiento
	int X = 6, Y = 5;
	int snake_tamanio = 1;
	int direccion = 1;
	int PX, PY;
	// Variable Thread para el envío de datos
	Envio_Datos envioParalelo;
	Coordenadas coorCuerpo;

	Thread enviarDatos;
	Thread movSnake;
	Thread juego;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJuego frame = new PantallaJuego();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaJuego() {
		/*
		 * Creando las variables para el uso del hilo y el envío de datos.
		 */
		envioParalelo = new Envio_Datos(X, Y);
		coorCuerpo = new Coordenadas(0, 0);

		enviarDatos = new Thread(envioParalelo);
		movSnake = new Thread(coorCuerpo);
		juego = new Thread(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tablero = new JLabel[12][12];
		panel = new JPanel();
		/* Inicializando la interfaz */
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 10, 360, 360);
		contentPane.add(panel);
		panel.setLayout(null);

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				JLabel casilla = new JLabel();
				casilla.setOpaque(true);
				casilla.setSize(30, 30);
				casilla.setLocation((i) * 30, (j) * 30);
				casilla.setBorder(new LineBorder(Color.WHITE));
				casilla.setBackground(Color.RED);
				/* Tablero de Juego */
				tablero[i][j] = new JLabel();
				tablero[i][j] = casilla;
				/* Fin Tablero Juego */
				panel.add(casilla);
			}
		}

		cuerpo = new ArrayList<Coordenadas>();
		cuerpo.add(new Coordenadas(6, 5));
		cuerpo.add(new Coordenadas(7, 5));
		cuerpo.add(new Coordenadas(8, 5));

		panel.revalidate();
		panel.repaint();

		juego.start();
		movSnake.start();
		enviarDatos.start();
		pintarComida();
		nivel1();
	}

	public void pintarComida() {
		PX = (int) (Math.random() * 10) + 1;
		PY = (int) (Math.random() * 10) + 1;
		while (tablero[PX][PY].getBackground() == Color.BLACK || tablero[PX][PY].getBackground() == Color.BLUE) {
			PX = (int) (Math.random() * 10) + 1;
			PY = (int) (Math.random() * 10) + 1;
		}
		tablero[PX][PY].setBackground(Color.LIGHT_GRAY);
		tablero[PX][PY].updateUI();
	}

	public void pintarSerpiente(int direccion) {
		switch (direccion) {
		case 1: // Izquierda
			if (X == 0) {
				X = 12;
			}
			X -= 1;
			break;
		case 2: // Derecha
			if (X >= 11) {
				X = 0;
			} else {
				X += 1;
			}
			break;
		case 3: // Arriba
			if (Y == 0) {
				Y = 12;
			}
			Y -= 1;
			break;
		case 4: // Abajo
			if (Y >= 11) {
				Y = 0;
			} else {
				Y += 1;
			}
			break;
		}

		int ActualX = X, ActualY = Y;
		int TempX, TempY;

		limpiar();
		for (int i = 0; i < cuerpo.size(); i++) {
			tablero[ActualX][ActualY].setBackground(Color.BLACK);
			envioParalelo.setX(ActualX);
			envioParalelo.setY(ActualY);
			TempX = cuerpo.get(i).ActualX;
			TempY = cuerpo.get(i).ActualY;
			cuerpo.get(i).ActualX = ActualX;
			cuerpo.get(i).ActualY = ActualY;
			ActualX = TempX;
			ActualY = TempY;
		}
		// Detectando Colisión
		if (PX == cuerpo.get(0).ActualX && PY == cuerpo.get(0).ActualY) {
			switch (direccion) {
			case 1: // Izquierda
				PX--;
				break;
			case 2: // Derecha
				PX++;
				break;
			case 3: // Abajo
				PY++;
				break;
			case 4: // Arriba
				PY--;
				break;
			}
			cuerpo.add(new Coordenadas(PX, PY));
			pintarComida();
		} else {
			/*
			 * Detectando colisiones con el cuerpo
			 */
			for (int i = 1; i < cuerpo.size(); i++) {
				if (cuerpo.get(0).ActualX == cuerpo.get(i).ActualX && cuerpo.get(0).ActualY == cuerpo.get(i).ActualY) {
					limpiar();
					JOptionPane.showMessageDialog(null, "GAME OVER");
					System.exit(0);
				}
			}

			/*
			 * Detectando colisiones con las paredes
			 */
			for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero.length; j++) {
					if(tablero[i][j].getBackground() != Color.BLUE) {
						j++;
					}else {
						if(cuerpo.get(0).ActualX == j && cuerpo.get(0).ActualY == i) {
							enviarDatos.stop();
							JOptionPane.showMessageDialog(null, "GAME OVER");
							System.exit(0);
						}
					}
				}
			}
		}
	}

	private void limpiar() {
		// TODO Auto-generated method stub
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (tablero[i][j].getBackground() != Color.LIGHT_GRAY && tablero[i][j].getBackground() != Color.BLUE)
					tablero[i][j].setBackground(Color.RED);
			}
		}
	}

	private void nivel1() {
		tablero[0][0].setBackground(Color.BLUE);
		tablero[0][1].setBackground(Color.BLUE);
		tablero[0][2].setBackground(Color.BLUE);
		tablero[0][3].setBackground(Color.BLUE);
		tablero[1][0].setBackground(Color.BLUE);
		tablero[2][0].setBackground(Color.BLUE);
		tablero[3][0].setBackground(Color.BLUE);

		tablero[0][11].setBackground(Color.BLUE);
		tablero[0][10].setBackground(Color.BLUE);
		tablero[0][9].setBackground(Color.BLUE);
		tablero[0][8].setBackground(Color.BLUE);
		tablero[1][11].setBackground(Color.BLUE);
		tablero[2][11].setBackground(Color.BLUE);
		tablero[3][11].setBackground(Color.BLUE);

		tablero[8][0].setBackground(Color.BLUE);
		tablero[9][0].setBackground(Color.BLUE);
		tablero[10][0].setBackground(Color.BLUE);
		tablero[11][0].setBackground(Color.BLUE);
		tablero[11][1].setBackground(Color.BLUE);
		tablero[11][2].setBackground(Color.BLUE);
		tablero[11][3].setBackground(Color.BLUE);

		tablero[8][11].setBackground(Color.BLUE);
		tablero[9][11].setBackground(Color.BLUE);
		tablero[10][11].setBackground(Color.BLUE);
		tablero[11][8].setBackground(Color.BLUE);
		tablero[11][9].setBackground(Color.BLUE);
		tablero[11][10].setBackground(Color.BLUE);
		tablero[11][11].setBackground(Color.BLUE);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					direccion = 1;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					direccion = 2;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					direccion = 4;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					direccion = 3;
				}
			}
		});
	}

	class Coordenadas implements Runnable {
		int ActualX, ActualY;

		public Coordenadas(int CabezaX, int CabezaY) {
			// TODO Auto-generated constructor stub
			ActualX = CabezaX;
			ActualY = CabezaY;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(500);
					pintarSerpiente(direccion);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
