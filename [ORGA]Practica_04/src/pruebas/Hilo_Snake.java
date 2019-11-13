/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MiriamLeticia
 */
public class Hilo_Snake extends Thread {
    
    public JLabel matriz;
    static int posX = 5;
    static int posY = 5 + 37*5;
    public int movimiento;
    public ArrayList<JLabel> Snake;
    public int comida;
    public Color c;
    public JPanel jPanel;
    
    public Hilo_Snake(JLabel _matriz, int _movimiento, ArrayList _snake, Color sa){
        this.matriz = _matriz;
        this.movimiento = _movimiento;
        this.Snake = _snake;
        this.c = sa;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(500);
                if(getComida() == 5 ){
                    JLabel aux1 = new JLabel();
                    aux1.setBounds(posX, posY, 35, 35);
                    aux1.setOpaque(true);
                    aux1.setBackground(c);
                    Snake.add(aux1);
                    setComida(0);
                }
                if(getMovimiento() == 1){//DERECHA
                        if(posX >= 5 + 37*11){
                            posX = 5;
                            matriz.setLocation(posX, posY);
                        }else{
                            posX = posX + 37;
                            matriz.setLocation(posX, posY);
                        }
                }
                if(getMovimiento() == 2){//ABAJO
                    if(posY >= 5 + 37*11){
                        posY = 5;
                        matriz.setLocation(posX, posY);
                    }else{
                        posY = posY + 37;
                        matriz.setLocation(posX, posY);
                    }
                }
                if(getMovimiento() == 3){//ARRIBA
                    if(posY <= 5){
                        posY = 5 + 37*11;
                        matriz.setLocation(posX, posY);
                    }else{
                        posY = posY - 37;
                        matriz.setLocation(posX, posY);
                    }
                }
                if(getMovimiento() == 4){//IZQUIERDA
                    if(posX <= 5){
                        posX = 5 + 37*11;
                        matriz.setLocation(posX, posY);
                    }else{
                        posX = posX - 37;
                        matriz.setLocation(posX, posY);
                    }
                }
                
            }catch(Exception e){
                System.out.println("Error:" + e);
            }
        }
    }

    public JLabel getMatriz() {
        return matriz;
    }

    public void setMatriz(JLabel matriz) {
        this.matriz = matriz;
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
