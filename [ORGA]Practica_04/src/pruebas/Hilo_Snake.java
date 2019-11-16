/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MiriamLeticia
 */
public class Hilo_Snake extends Thread {
    static int posX = 5 + 37*6;    // Posición X Inicial
    static int posY = 5 + 37*5;     // Posición Y Inicial
    public int movimiento;
    public ArrayList<JLabel> Snake;
    public int comida;
    public Color c;
    public JPanel jPanel;
    public JFrame prueba;
    
    public Hilo_Snake(int _movimiento, ArrayList _snake, Color sa, JFrame prueba){
        this.movimiento = _movimiento;
        this.Snake = _snake;
        this.c = sa;
        this.prueba = prueba;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(500);
                if(getComida() == 5){
                    JLabel aux1 = new JLabel();
                    aux1.setBounds(posX, posY, 35, 35);
                    aux1.setOpaque(true);
                    aux1.setBackground(c);  // Color de Snake = Verde Lima
                    Snake.add(aux1);
                    setComida(0);
                }
                if(getMovimiento() == 1){//DERECHA
                        if(posX >= 5 + 37*11){
                            posX = 5;
                        }else{
                            posX = posX + 37;
                            // Juego de Variables
                        int ActualX = posX;
                        int ActualY = posY;
                        int TempX = 0;
                        int TempY = 0;
                        /*Al momento de jugar con las posiciones*/
                        /*A la cabeza del snake, será su posición Actual
                        Mientras que la temporal, será la nueva posición dentro de si
                        */
                        for (int i = 0; i < Snake.size(); i++) {
                            TempX = Snake.get(i).getLocation().x;
                            TempY = Snake.get(i).getLocation().y;
                            Snake.get(i).setLocation(ActualX, ActualY);
                            ActualX = TempX;
                            ActualY = TempY;
                        }
                    }
                }
                if(getMovimiento() == 2){//ABAJO
                    if(posY >= 5 + 37*11){
                        posY = 5 - 37;
                    }else{
                        posY = posY + 37;
                        // Juego de Variables
                        int ActualY = posY;
                        int ActualX = posX;
                        int TempX = 0;
                        int TempY = 0;
                        
                        
                        /*Al momento de jugar con las posiciones*/
                        /*A la cabeza del snake, será su posición Actual
                        Mientras que la temporal, será la nueva posición dentro de si
                        */
                        for (int i = 0; i < Snake.size(); i++) {
                            TempX = Snake.get(i).getLocation().x;
                            TempY = Snake.get(i).getLocation().y;
                            Snake.get(i).setLocation(ActualX, ActualY);
                            ActualX = TempX;
                            ActualY = TempY;
                        }
                    }
                }
                if(getMovimiento() == 3){//ARRIBA
                    if(posY <= 5){
                        posY = 5 + 37*11;
                    }else{
                        posY = posY - 37;
                        // Juego de Variables
                        int ActualX = posX;
                        int ActualY = posY;
                        int TempX = 0;
                        int TempY = 0;
                        /*Al momento de jugar con las posiciones*/
                        /*A la cabeza del snake, será su posición Actual
                        Mientras que la temporal, será la nueva posición dentro de si
                        */
                        for (int i = 0; i < Snake.size(); i++) {
                            TempX = Snake.get(i).getLocation().x;
                            TempY = Snake.get(i).getLocation().y;
                            Snake.get(i).setLocation(ActualX, ActualY);
                            ActualX = TempX;
                            ActualY = TempY;
                        }
                    }
                }
                if(getMovimiento() == 4){//IZQUIERDA
                    if(posX <= 5){
                        posX = 5 + 37*12;
                    }else{
                        posX = posX - 37;
                        // Juego de Variables
                        int ActualX = posX;
                        int ActualY = posY;
                        int TempX = 0;
                        int TempY = 0;
                        /*Al momento de jugar con las posiciones*/
                        /*A la cabeza del snake, será su posición Actual
                        Mientras que la temporal, será la nueva posición dentro de si
                        */
                        for (int i = 0; i < Snake.size(); i++) {
                            TempX = Snake.get(i).getLocation().x;
                            TempY = Snake.get(i).getLocation().y;
                            Snake.get(i).setLocation(ActualX, ActualY);
                            ActualX = TempX;
                            ActualY = TempY;
                        }
                    }
                }
                
            }catch(Exception e){
                System.out.println("Error:" + e);
            }
        }
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    public ArrayList<JLabel> getSnake() {
        return Snake;
    }

    public void setSnake(ArrayList<JLabel> Snake) {
        this.Snake = Snake;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }
}
