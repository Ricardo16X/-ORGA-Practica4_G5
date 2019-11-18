package orga.practica_04;
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

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class PantallaJuego extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Variables de Inicializaci�n
	JLabel[][] tablero;
	ArrayList<Coordenadas> cuerpo;
	JPanel panel;
	JLabel lblPausa;
	JLabel lblPunteo;
        JButton btnRegresar;
        
	ArrayList<String> Obstaculos;
	// Variables de Movimiento
	int X = 6, Y = 5;
	int nivel = 1;
	int direccion = 1;
	int pausa = 0;
	int PX, PY;
	int punteo = 0;
	boolean cambioPunteo = false;
	// Variable Thread para el env�o de datos
	Envio_Datos envioParalelo;
	Coordenadas coorCuerpo;
	Tiempo tiempo;

	Thread enviarDatos;
	Thread movSnake;
	Thread juego;
	Thread mostrarTiempo;

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
		 * Creando las variables para el uso del hilo y el env�o de datos.
		 */
		coorCuerpo = new Coordenadas(0, 0);
		movSnake = new Thread(coorCuerpo);
		juego = new Thread(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		tablero = new JLabel[12][12];
		contentPane.setLayout(null);
		panel = new JPanel();
		panel.setBounds(10, 10, 360, 360);
		/* Inicializando la interfaz */
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTiempo = new JLabel("Tiempo: 0:0");
		lblTiempo.setBounds(380, 93, 144, 14);
		contentPane.add(lblTiempo);
		tiempo = new Tiempo(lblTiempo);
		mostrarTiempo = new Thread(tiempo);

		JLabel lblJugador = new JLabel("Jugador:");
		lblJugador.setBounds(380, 10, 144, 14);
		contentPane.add(lblJugador);

		lblPunteo = new JLabel("Punteo: 0");
		lblPunteo.setBounds(380, 174, 144, 14);
		contentPane.add(lblPunteo);

		lblPausa = new JLabel("PAUSA");
		lblPausa.setVisible(false);
		lblPausa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPausa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPausa.setBounds(380, 349, 144, 21);
		contentPane.add(lblPausa);
                
                

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

		envioParalelo = new Envio_Datos(X, tablero);
		enviarDatos = new Thread(envioParalelo);
		juego.start();
		movSnake.start();
		enviarDatos.start();
		mostrarTiempo.start();
		pintarSerpiente(1);
		pintarComida();
		nivel1();
	}

	private void azules() {
		Obstaculos = new ArrayList<>();
		String coordenadas1;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if(tablero[i][j].getBackground() == Color.BLUE) {
					coordenadas1 = i + "," + j;
					Obstaculos.add(coordenadas1);
				}
			}
		}
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
		if (direccion != 0) {
			limpiar();
			azules();
			
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

			for (int i = 0; i < cuerpo.size(); i++) {
				tablero[ActualX][ActualY].setBackground(Color.BLACK);
				TempX = cuerpo.get(i).ActualX;
				TempY = cuerpo.get(i).ActualY;
				cuerpo.get(i).ActualX = ActualX;
				cuerpo.get(i).ActualY = ActualY;
				ActualX = TempX;
				ActualY = TempY;
			}
			
			/*
			 * Detectando colisiones con las paredes
			 */
			/*for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero.length; j++) {
					if (tablero[i][j].getBackground() == Color.BLUE) {
						if (cuerpo.get(0).ActualX == j && cuerpo.get(0).ActualY == i) {
							limpiar();
							direccion = 0;
							JOptionPane.showMessageDialog(null, "GAME OVER");
						}
					}
				}
			}*/

			if(Obstaculos.size() > 0){
				String primero_serpiente = cuerpo.get(0).ActualX + "," + cuerpo.get(0).ActualY;
				boolean colision1 = Obstaculos.contains(primero_serpiente);
				if(colision1){
					JOptionPane.showMessageDialog(null, "GAME OVER");
				}
			}
			
			// Detectando Colisi�n con la Comida
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
				punteo += 5;
				lblPunteo.setText("Punteo: " + String.valueOf(punteo));
				cuerpo.add(new Coordenadas(PX, PY));
				pintarComida();
			} else {
				/*
				 * Detectando colisiones con el cuerpo
				 */
				for (int i = 1; i < cuerpo.size(); i++) {
					if (cuerpo.get(0).ActualX == cuerpo.get(i).ActualX
							&& cuerpo.get(0).ActualY == cuerpo.get(i).ActualY) {
						limpiar();
						direccion = 0;
						JOptionPane.showMessageDialog(null, "GAME OVER");
					}
				}
			}
			
			/*Reinicio de tama�o de snake si el punteo es igual a 50 o 100*/
			if(punteo == 50 && !cambioPunteo) {
				cambioPunteo = true;
				limpiar();
				nivel2();
				reiniciar();
				nivel++;
			}else if(punteo == 100 && cambioPunteo) {
				cambioPunteo = false;
				limpiar();
				nivel3();
				reiniciar();
				nivel++;
			}
		}
	}

	private void limpiar() {
		// TODO Auto-generated method stub
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (tablero[i][j].getBackground() != Color.LIGHT_GRAY)
					tablero[i][j].setBackground(Color.RED);
			}
		}
		switch(nivel) {
		case 1:
			nivel1();
			break;
		case 2:
			nivel2();
			break;
		case 3:
			nivel3();
			break;
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

	private void nivel2() {
		tablero[0][0].setBackground(Color.BLUE);
		tablero[0][1].setBackground(Color.BLUE);
		tablero[0][2].setBackground(Color.BLUE);
		tablero[0][3].setBackground(Color.BLUE);

		tablero[11][1].setBackground(Color.BLUE);
        tablero[11][2].setBackground(Color.BLUE);
        tablero[11][3].setBackground(Color.BLUE);
        tablero[11][0].setBackground(Color.BLUE);

        tablero[11][11].setBackground(Color.BLUE);
        tablero[11][10].setBackground(Color.BLUE);
        tablero[11][9].setBackground(Color.BLUE);
        tablero[11][8].setBackground(Color.BLUE);

        tablero[0][11].setBackground(Color.BLUE);
        tablero[0][10].setBackground(Color.BLUE);
        tablero[0][9].setBackground(Color.BLUE);
        tablero[0][8].setBackground(Color.BLUE);

        tablero[4][2].setBackground(Color.BLUE);
        tablero[5][2].setBackground(Color.BLUE);
        tablero[6][2].setBackground(Color.BLUE);
        tablero[7][2].setBackground(Color.BLUE);

        tablero[4][9].setBackground(Color.BLUE);
        tablero[5][9].setBackground(Color.BLUE);
        tablero[6][9].setBackground(Color.BLUE);
        tablero[7][9].setBackground(Color.BLUE);
	}
	
	private void nivel3() {
		tablero[0][0].setBackground(Color.BLUE);
        tablero[0][1].setBackground(Color.BLUE);
        tablero[0][2].setBackground(Color.BLUE);
        tablero[0][3].setBackground(Color.BLUE);
        tablero[1][0].setBackground(Color.BLUE);
        tablero[2][0].setBackground(Color.BLUE);
        tablero[3][0].setBackground(Color.BLUE);

        tablero[11][0].setBackground(Color.BLUE);
        tablero[10][0].setBackground(Color.BLUE);
        tablero[9][0].setBackground(Color.BLUE);
        tablero[8][0].setBackground(Color.BLUE);
        tablero[11][1].setBackground(Color.BLUE);
        tablero[11][2].setBackground(Color.BLUE);
        tablero[11][3].setBackground(Color.BLUE);

        tablero[11][11].setBackground(Color.BLUE);
        tablero[11][10].setBackground(Color.BLUE);
        tablero[11][9].setBackground(Color.BLUE);
        tablero[11][8].setBackground(Color.BLUE);
        tablero[10][11].setBackground(Color.BLUE);
        tablero[9][11].setBackground(Color.BLUE);
        tablero[8][11].setBackground(Color.BLUE);

        tablero[0][11].setBackground(Color.BLUE);
        tablero[1][11].setBackground(Color.BLUE);
        tablero[2][11].setBackground(Color.BLUE);
        tablero[3][11].setBackground(Color.BLUE);
        tablero[0][10].setBackground(Color.BLUE);
        tablero[0][9].setBackground(Color.BLUE);
        tablero[0][8].setBackground(Color.BLUE);

        for (int i = 3; i < 9; i++) {
            if (i != 5) {
                tablero[3][i].setBackground(Color.BLUE);
                tablero[8][i].setBackground(Color.BLUE);
            }
        }
	}
	
	private void reiniciar() {
		X = 6;
		Y = 5;
		cuerpo = new ArrayList<Coordenadas>();
		cuerpo.add(new Coordenadas(6, 5));
		cuerpo.add(new Coordenadas(7, 5));
		cuerpo.add(new Coordenadas(8, 5));
		pintarSerpiente(1);
		panel.revalidate();
		panel.repaint();
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
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (direccion != 0) {
						pausa = direccion;
						direccion = 0;
						lblPausa.setVisible(true);
					} else {
						direccion = pausa;
						lblPausa.setVisible(false);
					}
				} else if(e.getKeyCode() == KeyEvent.VK_R){
                                    PlayerForm O = new PlayerForm();
                                    
                                    O.setVisible(true);
                                    dispose();
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
					Thread.sleep(100);
					pintarSerpiente(direccion);
					System.out.println("CX :" + cuerpo.get(0).ActualX);
					System.out.println("CY: " + cuerpo.get(0).ActualY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class Tiempo implements Runnable {
		int S, M;
		JLabel mostrarTiempo;

		public Tiempo(JLabel mostrar) {
			// TODO Auto-generated constructor stub
			S = M = 0;
			mostrarTiempo = mostrar;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(1000);
					if(direccion != 0) {
						S++;
						if(S == 60) {
							S = 0;
							M++;
							if(M == 60) {
								M = 0;
							}
						}
						mostrarTiempo.setText("Tiempo: " + String.valueOf(S) + " : " + String.valueOf(M));
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
